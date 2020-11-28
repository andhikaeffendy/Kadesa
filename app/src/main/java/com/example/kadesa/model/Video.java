package com.example.kadesa.model;

public class Video {

    int id;
    String mImgVideo, mJudulVideo;

    public Video(int id, String mImgVideo, String mJudulVideo) {
        this.id = id;
        this.mImgVideo = mImgVideo;
        this.mJudulVideo = mJudulVideo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getmImgVideo() {
        return mImgVideo;
    }

    public void setmImgVideo(String mImgVideo) {
        this.mImgVideo = mImgVideo;
    }

    public String getmJudulVideo() {
        return mJudulVideo;
    }

    public void setmJudulVideo(String mJudulVideo) {
        this.mJudulVideo = mJudulVideo;
    }
}
