package com.InnerClass.Anonymous;

interface OnClickListener{
    void onClick();
}

abstract class OnDoubleClickListener{
    public void method(){
        System.out.println("something");
    }
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

        Button btn = new Button();
        OnClickListener onClickListener = new OnClickListener(){
            public void onClick(){
                System.out.println("Clicked");
            }
        };
        //onClickListener.onClick();
        btn.setOnClickListener(onClickListener);


       Button button = new Button();

       button.setOnClickListener(new OnClickListener(){
           public void onClick(){
               System.out.println("Clicked");
           }
           //static members are not allowed.
           //static final variables area allowed
           public static final int X = 10;
       });

       Button button2 = new Button(){
           @Override
           public void setOnClickListener(OnClickListener onClickListener){
               onClickListener.onClick();
               onClickListener.onClick();
           }
       };

       //lambda expression
       button2.setOnClickListener(()->{
               System.out.println("Button2 Clicked");
       });


    }
}
