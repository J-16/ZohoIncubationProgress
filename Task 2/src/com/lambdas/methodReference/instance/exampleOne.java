package com.lambdas.methodReference.instance;


interface Lambda{
    String method(String x);
}

class LambdaMethodReference {

    public String isValid(String x) {
        if (x.matches(".*[0-9].*"))
            return "In Valid";
        return "Valid";
    }
}

public class exampleOne {

    public static void main(String[] args) {

        LambdaMethodReference lambdaMethodReference = new LambdaMethodReference();
        Lambda lambda;

        lambda = lambdaMethodReference::isValid;
        System.out.println(lambda.method("Random"));

    }

}
