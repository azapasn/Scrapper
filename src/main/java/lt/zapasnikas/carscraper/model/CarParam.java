package lt.zapasnikas.carscraper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class CarParam {
    @Id
    @GeneratedValue
    @Column
    private long id;
    @JsonIgnore
    @OneToOne
    private Advertisement advertisement;
    @Column
    private String make;
    @Column
    private String model;
    @Column
    private String yearsProd;
    @Column
    private String engine;
    @Column
    private String fuelType;
    @Column
    private String color;
    @Column
    private String mileageKm;
    @Column
    private String driveTrain;
    @Column
    private String defects;
    @Column
    private String vinCode;
    @Column
    private String firstRegistrationCountry;
    @Column
    private String licencePlate;

    public String getYearsProd() {
        return yearsProd;
    }

    public void setYearsProd(String yearsProd) {
        this.yearsProd = yearsProd;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMileageKm() {
        return mileageKm;
    }

    public void setMileageKm(String mileageKm) {
        this.mileageKm = mileageKm;
    }

    public String getDriveTrain() {
        return driveTrain;
    }

    public void setDriveTrain(String driveTrain) {
        this.driveTrain = driveTrain;
    }

    public String getDefects() {
        return defects;
    }

    public void setDefects(String defects) {
        this.defects = defects;
    }

    public String getVinCode() {
        return vinCode;
    }

    public void setVinCode(String vinCode) {
        this.vinCode = vinCode;
    }

    public String getFirstRegistrationCountry() {
        return firstRegistrationCountry;
    }

    public void setFirstRegistrationCountry(String firstRegistrationCountry) {
        this.firstRegistrationCountry = firstRegistrationCountry;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }
}
