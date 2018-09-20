import org.apache.commons.codec.binary.Base64;

import java.text.SimpleDateFormat;
import java.util.Arrays;

public class App {
    private static final String privateKey = "30820276020100300d06092a864886f70d0101010500048202603082025c02010002818100c535627ca25851ab88122a53c9f84b267f86357c180ee46ecb532c9458f049da044b79e7c25ab29ec542e1607e10fa636a9da89566c7464ab86af1ccd1e26338a32f257db0eb8a58782dacefb17d255a0bb171936f563d1e1efda9ef1ac02bc1df879625ff5c75138dc841e58aee228a3abe969dfc78b93f70cf0d36f1ac7e7b020301000102818027f2f48c82e69944c23fcbcb4572c2e67d96d666d908cb459672b6e9264b26eabf4bfad9f409237dfe3fda200de9aa8f511db6f4ece91958319b9878015c76f4bb22750ed3e3bde8fe340559dcfc27b0acad50c49b56949ec4a413cd3bde7039986383ab5b91a35e07e0ddaecfc273d3c38e884ac9629c8bda03f7a749daa3e1024100e8e030b7e7e7b06306624b0fe0b81046eddb3e1d56f3044304c827f7997f7d7069f3c03c66bf5e2cca589f48802b1094dff84e8b03f5231c1f2922658e37a48d024100d8ca8471b18478e9b7e3397f8298b7f5f425b226cd46ea630faa0b8b5f65ef07f0e3be2d7c987b690da8737e01de12feda8994f2edc40803918441737d7d61270240169799625e17361f854107f33463e7005a970881a42bd3600518bdf7e16662ef02f868cff34fe17d0aec6a178887a5062c6c54825c11ee240e49d9526ce7ad09024100c4307a2d9dd5e1cab914945b55a7b98b86f87a41ed31c939feda077a88c9e9c8c8f281900e9abbcc7584c587fa4eabe82183b2dd29d966db3bdc7192ffb4f0af02401a723ed71318f8084e6bc2ddabbe226c71a1da4472477ed578a53c1d39f5a1f2c606fb264e267cb0293a0e9dad031f38da26a9a82aa89871d65966884c280a55";
    public static void main(String[] args) throws Exception {
        String str = "eyJqdGkiOiJiMjI5YmJiMmFkYWE0OGU3YmE2MDAzMmE1MDM2YzQwZCIsImV4cCI6MTUxOTcwMzIwNjAwMCwicm9sZXMiOlsiUk9MRV9BRE1VQ19BRE1JTiJdLCJ1c2VyX2lkIjoxfQ==";
        byte[] bytes = Base64.decodeBase64(str.getBytes());
        String result = new String(bytes,"UTF-8");
        System.out.println("result:\r\n"+result);

        String str2 = "12c6ff20d140b3895db9aacc331467e40512213ca02551009164ab1fd92ca09f";
        System.out.println("str2:\r\n"+str2.length());
        byte[] b = {49, 50, 99, 54, 102, 102, 50, 48, 100, 49, 52, 48, 98, 51, 56, 57, 53, 100, 98, 57, 97, 97, 99, 99, 51, 51, 49, 52, 54, 55, 101, 52, 48, 53, 49, 50, 50, 49, 51, 99, 97, 48, 50, 53, 53, 49, 48, 48, 57, 49, 54, 52, 97, 98, 49, 102, 100, 57, 50, 99, 97, 48, 57, 102};
        System.out.println("b:\r\n"+b.length);
        byte[] copy = Arrays.copyOf(b, 10);
        System.out.println(Arrays.toString(copy));
        byte[] range = Arrays.copyOfRange(b, 2, 12);
        System.out.println(Arrays.toString(range));

    }
}
