package models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "line")
public class Line {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "Distance", nullable = false)
    private Integer distance;

    @Column(name = "`Bus-Stop`", nullable = false)
    private Integer busStop;

    public Line() {
    }

    public Line(Integer id, Integer distance, Integer busStop) {
        this.id = id;
        this.distance = distance;
        this.busStop = busStop;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getBusStop() {
        return busStop;
    }

    public void setBusStop(Integer busStop) {
        this.busStop = busStop;
    }

    @Override
    public String toString() {
        return "Line{" +
                "id=" + id +
                ", distance=" + distance +
                ", busStop=" + busStop +
                '}';
    }
}