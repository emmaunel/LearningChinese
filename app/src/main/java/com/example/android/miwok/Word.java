package com.example.android.miwok;

/**
 * Created by Ayobami on 12/9/2016.
 */

public class Word {

   private String english;
    private String chinese;
    private int mAudioResourceId;
    private static final int NO_IMAGE_RESOURCE = -1;
    private int image = NO_IMAGE_RESOURCE;

    public Word(String mEnglish, String mChinese, int audioResourceId){
        chinese = mChinese;
        english = mEnglish;
        mAudioResourceId = audioResourceId;

    }

    public Word(String mEnglish, String mChinese, int mImage,  int audioResourceId){
        chinese = mChinese;
        english = mEnglish;
        image = mImage;
        mAudioResourceId = audioResourceId;
    }

    public Word(String english){
        this.english = english;
    }

    public Word(String english, int image){
        this.english = english;
        this.image = image;
    }

    //get chinese translation
    public String getChinese() {
        return chinese;
    }

    //get english translation
    public String getEnglish(){
        return english;
    }

    //get image
    public int getImage(){
        return image;
    }

    public boolean hasImage(){
        return image != NO_IMAGE_RESOURCE;
    }

    public int getAudioResourceId(){
        return mAudioResourceId;
    }

    @Override
    public String toString() {
        return "English= " + english + " ," + "\t" +
                " Chinese= " + chinese ;
    }
}
