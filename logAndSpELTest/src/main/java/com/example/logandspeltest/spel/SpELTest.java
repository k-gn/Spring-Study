package com.example.logandspeltest.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

@Component
public class SpELTest implements CommandLineRunner {

    // @Value 애노테이션에서 SpEL 사용
    @Value("#{1+1}")
    int value;

    @Value("#{'hello ' + 'world'}")
    String greeting;

    @Value("#{1 eq 5}")
    boolean trueOrFalse;

    @Value("Literal String")
    String literalString;

    @Value("#{inventor.num}")
    int num1;

    @Value("#{inventor.add()}")
    int num2;


    @Override
    public void run(String... args) throws Exception {
        System.out.println(value);
        System.out.println(greeting);
        System.out.println(trueOrFalse);
        System.out.println(literalString);
        System.out.println(num1);
        System.out.println(num2);

        System.out.println("=============================");

        // Expression을 이용한 SpEL 파싱
//        ExpressionParser parser = new SpelExpressionParser();
//        Expression expression = parser.parseExpression("1+1");
//        Object value = expression.getValue();
//        System.out.println(value);    // 2

        // EvaluationContext를 이용한 SpEL 파싱
        // name, nationality를 파라미터로 갖는 생성자
        Inventor tesla = new Inventor("Nikola Tesla","Serbian", 2);

        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("name1"); // name 프로퍼티

        // Context에 tesla객체를 넣어준다.
        EvaluationContext context = new StandardEvaluationContext(tesla);
        String name1 = (String) exp.getValue(context); //name = "Nikola Tesla"
        System.out.println(name1);  // Nikola Tesla

        // getValue 메서드 호출 시 StandardEvaluationContext를 사용하지 않고 객체를 직접 지정
        String name2 = (String) exp.getValue(tesla);
        System.out.println(name2);  // Nikola Tesla
        // StandardEvaluationContext를 사용하는 경우 생성 비용이 발생하지만 필드에 대해 캐싱하기 때문에 반복적으로 사용하면 표현식 파싱이 더 빠르다는 장점이 있다.
    }
}

/*
 - #{ SpEL표현식 }
 - SpEL 지원 기능
    리터럴 표현식 (Literal Expression)
    Boolean과 관계연산자 (Boolean and Relational Operator)
    정규 표현식 (Regular Expression)
    클래스 표현식 (Class Expression)
    프로퍼티, 배열, 리스트, 맵에 대한 접근 지원 (Accessing properties, arrays, lists, maps)
    메서드 호출 (Method Invocation)
    관계연산자 (Relational Operator)
    할당 (Assignment)
    생성자 호출 (Calling Constructors)
    Bean 참조 (Bean References)
    배열 생성 (Array Contruction)
    인라인 리스트/맵 (Inline List/Map)
    삼항 연산자 (Ternary Operator)
    변수 (Variables)
    사용자 정의 함수 (User defined functions)
    Collections Projection
    Collections Selection
    Templated expression

  - #{빈id.프로퍼티} or @빈id.프로퍼티 형식으로 빈 참조
  - ${"프로퍼티"} 형식으로 프로퍼티 참조

  - 프로퍼티, 메서드, 필드를 처리하고 타입변환을 수행하는 표현식을 평가할 때 EvaluationContext 인터페이스를 사용
  - StandardEvaluationContext는 setRootObject() 메서드나 생성자에 루트객체를 전달해서 평가에 사용할 루트객체를 지정하는 곳이다.
    setVariable()와 registerFunction() 메서드를 사용해서 표현식에서 사용할 변수나 함수를 지정할 수도 있다.


*/