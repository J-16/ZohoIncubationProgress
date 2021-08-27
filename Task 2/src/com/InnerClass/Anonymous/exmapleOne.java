package com.InnerClass.Anonymous;

interface OnClickListener{
    void onClick();
}

abstract class OnDoubleClickListener{
    public abstract void onDoubleClick();
}

class Button{
    public void setOnClickListener(OnClickListener onClickListener){
        onClickListener.onClick();
    }
    public void setOnDoubleClick(OnDoubleClickListener OnDoubleClickListener){
        OnDoubleClickListener.onDoubleClick();
    }
}

public class exmapleOne {

    public static void main(String[] args){

        OnClickListener onClickListener = new OnClickListener(){
            public void onClick(){
                System.out.println("Clicked");
            }
        };

        onClickListener.onClick();
        
        Button button = new Button();
        button.setOnClickListener(new OnClickListener(){
            public void onClick(){
                System.out.println("Clicked");
            }
        });
    }
}
