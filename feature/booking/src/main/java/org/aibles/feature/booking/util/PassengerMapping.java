package org.aibles.feature.booking.util;

import org.aibles.feature.booking.entity.PassengerInfo;
import org.aibles.feature.booking.framework.datasource.PassengerInfoDatabaseEntity;


public class PassengerMapping {

    public static PassengerInfo convertDatabaseToEntity(PassengerInfoDatabaseEntity databaseEntity) {
        PassengerInfo passengerInfo = new PassengerInfo();
        passengerInfo.setId(databaseEntity.getId());
        passengerInfo.setFirstName(databaseEntity.getFirstName());
        passengerInfo.setMiddleName(databaseEntity.getMiddleName());
        passengerInfo.setLastName(databaseEntity.getLastName());
        passengerInfo.setDateOfBirth(databaseEntity.getDateOfBirth());
        passengerInfo.setGender(databaseEntity.getGender());
        passengerInfo.setCountry(databaseEntity.getCountry());
        return passengerInfo;
    }

}
