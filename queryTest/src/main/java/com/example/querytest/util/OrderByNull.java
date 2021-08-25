package com.example.querytest.util;

import com.querydsl.core.types.NullExpression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;

// 별도의 Order 클래스를 생성
// NullExpression.DEFAULT - null을 그냥 넣게 되면 Querydsl의 정렬을 담당하는 OrderSpecifier 에서 제대로 처리하지 못한다.
//                          Querydsl에서는 공식적으로 null에 대해 NullExpression.DEFAULT 클래스로 사용하길 권장한다.
public class OrderByNull extends OrderSpecifier {// custom 정렬 지정자, 정렬 지정타입

    public static final OrderByNull DEFAULT = new OrderByNull();

    private OrderByNull(){
        super(Order.ASC, NullExpression.DEFAULT, NullHandling.Default);
    }
}