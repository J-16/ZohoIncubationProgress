#include<iostream>
#include<bits/stdc++.h>

using namespace std;

struct Student{
  string name;
  int rollNo;
  int maths[3];
  int cs[3];
};

void display(Student student){
  printf("Name: %d rollNo: %d",student.name,student.rollNo);
}

void setCIA1Mark(Student &student, int mathsMark, int csMark){
  student.maths[0] = mathsMark;
  student.cs[0] = csMark;
}

void setCIA2Mark(Student &student, int mathsMark, int csMark){
  student.maths[1] = mathsMark;
  student.cs[1] = csMark;
}

void setCIA3Mark(Student &student, int mathsMark, int csMark){
  student.maths[2] = mathsMark;
  student.cs[2] = csMark;
}

void total(Student student){
  printf("Total: %d", *max_element(student.maths, student.maths+3) + *max_element(student.cs, student.cs+3) );
}

int main(){
  
  Student student1[60];
  student1[0].name="abishek";
  student1[0].rollNo = 1001;
  setCIA1Mark(student1[0], 40,40);
  setCIA2Mark(student1[0], 40,40);
  setCIA3Mark(student1[0], 50,50);
  student1[0].maths[0] = 100; // avoiding this use class // access modifiers helps us
  total(student1[0]);

}