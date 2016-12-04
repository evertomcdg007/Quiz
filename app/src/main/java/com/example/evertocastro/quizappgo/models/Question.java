package com.example.evertocastro.quizappgo.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Everto Castro on 27/11/2016.
 */

public class Question implements Parcelable{

    private Integer id;
    private String image;
    private String question;
    private String item1;
    private String item2;
    private String item3;
    private String item4;
    private Integer itemCorrect;

    public Question(Integer id, String image, String question, String item1, String item2, String item3, String item4, Integer itemCorrect) {
        this.id = id;
        this.image = image;
        this.question = question;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.itemCorrect = itemCorrect;
    }

    public Question( String image, String question, String item1, String item2, String item3, String item4, Integer itemCorrect) {
        this.image = image;
        this.question = question;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.itemCorrect = itemCorrect;
    }

    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>(){
          public Question createFromParcel(Parcel parcel){
           return new Question(parcel);
          }

          public Question[] newArray(int size){
              return new Question[size];
          }
    };

    private Question(Parcel parcel){
        setId(parcel.readInt());
        setImage(parcel.readString());
        setQuestion(parcel.readString());
        setItem1(parcel.readString());
        setItem2(parcel.readString());
        setItem3(parcel.readString());
        setItem4(parcel.readString());
        setItemCorrect(parcel.readInt());

    }

    public Question() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem3() {
        return item3;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public String getItem4() {
        return item4;
    }

    public void setItem4(String item4) {
        this.item4 = item4;
    }

    public Integer getItemCorrect() {
        return itemCorrect;
    }

    public void setItemCorrect(Integer itemCorrect) {
        this.itemCorrect = itemCorrect;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getId());
        parcel.writeString(getImage());
        parcel.writeString(getQuestion());
        parcel.writeString(getItem1());
        parcel.writeString(getItem2());
        parcel.writeString(getItem3());
        parcel.writeString(getItem4());
        parcel.writeInt(getItemCorrect());

    }
}
