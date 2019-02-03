package org.theta4j.osc;

import okhttp3.Headers;
import org.apache.commons.io.input.BoundedInputStream;

import javax.annotation.Nonnull;
import java.io.*;
import java.util.Objects;

/**
 * Motion JPEG stream. This class is not thread-safe.
 */
public final class MJpegInputStream implements Closeable {
    private static final String CHARSET = "UTF-8";

    private static final String HEADER_CONTENT_LENGTH = "Content-Length";

    private final String boundary;

    private final InputStream is;

    private MJpegInputStream(final String boundary, final InputStream is) {
        this.boundary = boundary;
        this.is = new DataInputStream(new BufferedInputStream(is));
    }

    static MJpegInputStream fromHttpStream(final String boundary, final InputStream inputStream) {
        Objects.requireNonNull(boundary, "boundary can not be null.");
        Objects.requireNonNull(inputStream, "inputStream can not be null.");

        return new MJpegInputStream(boundary, inputStream);
    }

    /**
     * Returns next frame data.
     * Each frame's {@link InputStream#close()} will not be propagated to {@link MJpegInputStream#close()}.
     * You must call {@link MJpegInputStream#close()} to terminate stream.
     *
     * @return InputStream returns next frame data.
     * @throws IOException I/O error is occurred.
     */
    @Nonnull
    public InputStream nextFrame() throws IOException {
        skipBoundaryLines();
        final Headers headers = readHeaders();
        final int contentLength = Integer.parseInt(headers.get(HEADER_CONTENT_LENGTH));
        final BoundedInputStream bis = new BoundedInputStream(is, contentLength);
        bis.setPropagateClose(false);
        return bis;
    }

    /**
     * Close stream.
     *
     * @throws IOException I/O error is occurred.
     */
    @Override
    public void close() throws IOException {
        is.close();
    }

    private void skipBoundaryLines() throws IOException {
        for (; ; ) {
            final String line = readLine();
            if (line.isEmpty()) {
                continue;
            } else if (line.equals(boundary)) {
                return;
            }
            throw new IOException("expect " + boundary + ", but got " + line);
        }
    }

    private Headers readHeaders() throws IOException {
        final Headers.Builder builder = new Headers.Builder();
        for (; ; ) {
            final String line = readLine();
            if (line.isEmpty()) {
                break;
            }
            builder.add(line);
        }
        return builder.build();
    }

    private String readLine() throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        for (; ; ) {
            final int c = is.read();
            if (c == '\r') {
                continue;
            }
            if (c == '\n') {
                break;
            }
            baos.write((char) c);
        }
        return baos.toString(CHARSET);
    }
}
