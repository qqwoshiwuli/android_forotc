package com.gqfbtc.server.listener;


public interface UpdateProgressListener {
    /**
     * download start
     */
    void start();

    /**
     * update download progress
     * @param progress
     */
    void update(int progress);

    /**
     * download success
     */
    void success();

    /**
     * download error
     */
    void error();
}
