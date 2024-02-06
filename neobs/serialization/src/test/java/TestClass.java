import com.aperlab.serialization.Codec;
import com.aperlab.serialization.codecs.ObjectCodec;
import lombok.Data;


@Data
public class TestClass {

    private Codec<TestClass> CODEC = ObjectCodec
            .of(TestClass.class)
            .constructor(TestClass::new, b-> {
                //b.property("intName", TestClass::getNum, Codec.INT);
                //b.property("StringName", TestClass::getStr1, Codec.INT);
            })
            .property("OtherString", TestClass::getStr2, TestClass::setStr2, Codec.STRING)
            .build();

    public int num = 10;
    public String str1 = "asd";
    public String str2 = "asd";
    public String str3 = "asd";

    public TestClass(int num, String str1) {
        this.num = num;
        this.str1 = str1;
    }
}
