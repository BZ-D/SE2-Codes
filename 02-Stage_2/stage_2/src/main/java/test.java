public class test {
    String m_kobe = "1";
    String[] m_king = { "2" };

    public static void main(String[] a){
        test t = new test();
        t.mb_operate(t.m_kobe, t.m_king);
        System.out.println(t.m_kobe + t.m_king[0]);
    }

    static void mb_operate(String kobe, String[] king) {
        kobe = new String("3");
        king[0] = new String("4");
    }
}
