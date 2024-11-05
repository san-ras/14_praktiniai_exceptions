package application;

import java.util.ArrayList;
import java.util.List;

public class AverageSensor implements Sensor {

    private List<Sensor> sensors;
    private List<Integer> readings;


    public AverageSensor() {
        this.sensors = new ArrayList<>();
        this.readings = new ArrayList<>();
    }

    @Override
    public boolean isOn() {
        return sensors.stream().allMatch(Sensor::isOn);
    }

    @Override
    public void setOn() {
        sensors.forEach(Sensor::setOn);
    }

    @Override
    public void setOff() {
        sensors.forEach(Sensor::setOff);
    }

    @Override
    public int read() {
        if (isOn()) {
            int reading = (int) sensors.stream().mapToInt(Sensor::read).average().orElseThrow(IllegalStateException::new);
            readings.add(reading);
            return reading;
        } else throw new IllegalStateException();
    }

    public List<Integer> readings() {
        return this.readings;
    }

    public void addSensor(Sensor toAdd) {
        this.sensors.add(toAdd);
    }
}
