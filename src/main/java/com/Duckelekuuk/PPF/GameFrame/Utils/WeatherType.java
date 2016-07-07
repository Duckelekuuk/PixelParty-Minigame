package com.Duckelekuuk.PPF.GameFrame.Utils;

import org.bukkit.World;

/**
 * @AUTHOR: Duckelekuuk
 * Copyright © 2016, Duco Lindner, All rights reserved.
 */

public enum WeatherType {

    SUNNY,
    STORM,
    RAIN;

    public static void setWeather(World world, WeatherType weatherType) {
        switch (weatherType) {
            case SUNNY:
                world.setStorm(false);
                world.setThundering(false);
                world.setWeatherDuration(2000000000);
                break;

            case STORM:
                world.setStorm(true);
                world.setThundering(true);
                world.setWeatherDuration(2000000000);
                break;

            case RAIN:
                world.setStorm(true);
                world.setThundering(false);
                world.setWeatherDuration(2000000000);
                break;
        }
    }
}
