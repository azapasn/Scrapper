package lt.zapasnikas.carscraper.model;

import com.google.common.truth.Truth;
import org.junit.Before;
import org.junit.Test;

public class CarParamTest {
    CarParam carParam;
    Advertisement advertisement = new Advertisement();

    @Before
    public void prepareCarParam() {
        carParam = new CarParam();
        carParam.setModel("model");
        carParam.setMake("make");
        carParam.setColor("color");
        carParam.setDefects("defects");
        carParam.setDriveTrain("driveTrain");
        carParam.setEngine("engine");
        carParam.setFirstRegistrationCountry("country");
        carParam.setFuelType("fuel");
        carParam.setMileageKm("mileage");
        carParam.setVinCode("vin");
        carParam.setYearsProd("years");
        carParam.setAdvertisement(advertisement);
        carParam.setId(1);
        carParam.setLicencePlate("ABC123");
    }

    @Test
    public void getterAndSetterTest() {
        Truth.assertThat(carParam).isEqualTo(carParam);
        Truth.assertThat(carParam.getModel()).isEqualTo("model");
        Truth.assertThat(carParam.getMake()).isEqualTo("make");
        Truth.assertThat(carParam.getColor()).isEqualTo("color");
        Truth.assertThat(carParam.getDefects()).isEqualTo("defects");
        Truth.assertThat(carParam.getDriveTrain()).isEqualTo("driveTrain");
        Truth.assertThat(carParam.getEngine()).isEqualTo("engine");
        Truth.assertThat(carParam.getFirstRegistrationCountry()).isEqualTo("country");
        Truth.assertThat(carParam.getFuelType()).isEqualTo("fuel");
        Truth.assertThat(carParam.getMileageKm()).isEqualTo("mileage");
        Truth.assertThat(carParam.getVinCode()).isEqualTo("vin");
        Truth.assertThat(carParam.getYearsProd()).isEqualTo("years");
        Truth.assertThat(carParam.getId()).isEqualTo(1);
        Truth.assertThat(carParam.getLicencePlate()).isEqualTo("ABC123");
    }

    @Test
    public void setterFailTest() {
        Truth.assertThat(carParam).isNotEqualTo(new CarParam());
        Truth.assertThat(carParam.getModel()).isNotEqualTo("newModel");
        Truth.assertThat(carParam.getMake()).isNotEqualTo("newMake");
        Truth.assertThat(carParam.getColor()).isNotEqualTo("newColor");
        Truth.assertThat(carParam.getDefects()).isNotEqualTo("newDefects");
        Truth.assertThat(carParam.getDriveTrain()).isNotEqualTo("newDriveTrain");
        Truth.assertThat(carParam.getEngine()).isNotEqualTo("newEngine");
        Truth.assertThat(carParam.getFirstRegistrationCountry()).isNotEqualTo("newCountry");
        Truth.assertThat(carParam.getFuelType()).isNotEqualTo("newFuel");
        Truth.assertThat(carParam.getMileageKm()).isNotEqualTo("newMileage");
        Truth.assertThat(carParam.getVinCode()).isNotEqualTo("newVin");
        Truth.assertThat(carParam.getYearsProd()).isNotEqualTo("newYears");
        Truth.assertThat(carParam.getId()).isNotEqualTo(2);
        Truth.assertThat(carParam.getLicencePlate()).isNotEqualTo("newLicencePlate");
    }

}