package com.hs.datatrans.utils.dimeng.utils;

public enum FileType {
    JPEG("FFD8FF"),
    JPG("FFD8FF"),
    PNG("89504E47"),
    GIF("47494638"),
    TIFF("49492A00"),
    BMP("424D"),
    DWG("41433130"),
    PSD("38425053"),
    RTF("7B5C727466"),
    XML("3C3F786D6C"),
    HTML("68746D6C3E"),
    EML("44656C69766572792D646174653A"),
    DBX("CFAD12FEC5FD746F"),
    PST("2142444E"),
    XLS("D0CF11E0"),
    XLSX("D0CF11E0"),
    MDB("5374616E64617264204A"),
    WPD("FF575043"),
    EPS("252150532D41646F6265"),
    PDF("255044462D312E"),
    QDF("AC9EBD8F"),
    PWL("E3828596"),
    ZIP("504B03041400000008005A"),
    RAR("52617221"),
    WAV("57415645"),
    AVI("41564920"),
    RAM("2E7261FD"),
    RM("2E524D46"),
    MPG("000001BA"),
    FLV("464C5601050000000900"),
    MP4("0000002066747970"),
    MOV("6D6F6F76"),
    ASF("3026B2758E66CF11"),
    MID("4D546864"),
    TXT("61647366617364"),
    APK("504B030414"),
    DOCX("504B0304140006000800"),
    DOC("D0CF11E0A1B11AE10000");

    private String value = "";

    private FileType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
