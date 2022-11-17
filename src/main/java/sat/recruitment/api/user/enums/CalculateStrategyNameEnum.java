package sat.recruitment.api.user.enums;



public enum CalculateStrategyNameEnum {

    NORMAL("NORMAL"," USER NORMAL"),
    SUPERUSER("SUPERUSER","USER SUPER USER "),
    PREMIUN("PREMIUN", "USER PREMIUN");

    private String code;
    private String description;

    CalculateStrategyNameEnum(String code, String description){
        this.code = code;
        this.description = description;
    }

    public static CalculateStrategyNameEnum getByCode(String code) {

        if(code == null){
            return null;
        }

        CalculateStrategyNameEnum[] values = values();
        for (CalculateStrategyNameEnum enumValue :values ) {
            if (enumValue.getCode().equalsIgnoreCase(code)) {
                return enumValue;
            }
        }
        return null;
    }
    public String getCode() {
        return code;
    }
}
