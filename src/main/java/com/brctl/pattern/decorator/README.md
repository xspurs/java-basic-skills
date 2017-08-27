# 装饰器模式

`Client` -- 场景类

`ScoreSheet` -- 成绩单(接口，也可为抽象类)

`PrimarySchoolScoreSheet` -- 小学成绩单(实现类)

`Decorator` -- 装饰器(抽象类)

`HighScoreDecorator` -- 最高分成绩单装饰器(实现类，扩充成绩单功能，即对其进行包装)

`SortDecorator` -- 班级排名成绩单装饰器(实现类，扩充成绩单功能，即对其进行包装)

---

***Decorator Pattern***:

Attach additional responsibilities to an object *dynaically* keeping the same interface. Decorators provide a flexible *alternative* to subclassing for extending functionality.

装饰器模式是继承关系的一种替代方案。