package com.lambdas.methodReference.instance;


interface Lambda{
    boolean method(String x);
}

class LambdaMethodReference {

    public boolean isValid(String x) {
        if (x.matches(".*[0-9!@#$%^&*()_+].*"))
            return false;
        return true;
    }
}

public class exampleOne {

    public static void main(String[] args) {

        LambdaMethodReference lambdaMethodReference = new LambdaMethodReference();

        Lambda lambda = lambdaMethodReference::isValid;
        System.out.println(lambda.method("Random"));

    }

}
