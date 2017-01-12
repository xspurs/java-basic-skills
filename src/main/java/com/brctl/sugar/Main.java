package com.brctl.sugar;

/**
 * Java语法糖之
 * 自动装箱与自动拆箱
 * Created by duanxiaoxing on 16/12/18.
 */
public class Main {

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = -129;
        Integer f = -129;
        Long g = 3L;

        System.out.println(c == d);  // 不会自动拆箱，但因为有小整数池(范围[-128, 127])的存在，true
        System.out.println(e - 1 == f - 1);  // 自动拆箱，true
        System.out.println(e == f);  // 不会自动拆箱，超过了小整数池的范围，false
        System.out.println(c == a + b);  // 自动拆箱，true
        System.out.println(c.equals(a + b));  // true
        System.out.println(g.equals(a + b));  // 不会自动处理数据转型的关系，false
        System.out.println(g == a + b);  // 自动拆箱，true
    }
}


// javac之后的结果（反编译）：
/*
package com.brctl.sugar;

import java.io.PrintStream;

public class Main
{
  public static void main(String[] paramArrayOfString)
  {
    Integer localInteger1 = Integer.valueOf(1);
    Integer localInteger2 = Integer.valueOf(2);
    Integer localInteger3 = Integer.valueOf(3);
    Integer localInteger4 = Integer.valueOf(3);
    Integer localInteger5 = Integer.valueOf(-129);
    Integer localInteger6 = Integer.valueOf(-129);
    Long localLong = Long.valueOf(3L);

    System.out.println(localInteger3 == localInteger4);
    System.out.println(localInteger5.intValue() - 1 == localInteger6.intValue() - 1);
    System.out.println(localInteger5 == localInteger6);
    System.out.println(localInteger3.intValue() == localInteger1.intValue() + localInteger2.intValue());
    System.out.println(localInteger3.equals(Integer.valueOf(localInteger1.intValue() + localInteger2.intValue())));
    System.out.println(localLong.equals(Integer.valueOf(localInteger1.intValue() + localInteger2.intValue())));
    System.out.println(localLong.longValue() == localInteger1.intValue() + localInteger2.intValue());
  }
}
 */
