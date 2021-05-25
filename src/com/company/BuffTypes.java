package com.company;

public class BuffTypes {
    public static final int FastHealthRegeneration = 0;
    public static final int FastStaminaRegeneration = 1;
    public static final int EnLargeMaxHealth = 2;
    public static final int EnLargeMaxStamina = 3;

    public static String[] buffImageNames = new String[]{"/health_buff_icon_20x20.png", "/stamina_buff_icon_20x20.png", "/max_health_buff_icon_20x20.png", "/max_stamina_buff_icon_25x18.png" };
    public static String[] toolTips = new String[]{"Регенерация здоровья увеличена на 100%", "Восстановление энергии увеличено на 100%", "Максимальное здоровье увеличено на 50%", "Максимальная энергия увеличена на 50%"};
}
