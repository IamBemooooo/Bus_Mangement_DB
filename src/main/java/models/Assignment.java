package models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "assignment")
public class Assignment {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_Line", nullable = false)
    private Line idLine;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_Driver", nullable = false)
    private Driver idDriver;

    @Column(name = "Day", nullable = false)
    private LocalDate day;

    @Column(name = "Turn", nullable = false)
    private Integer turn;

    public Assignment(Integer id,Line idLine, LocalDate day, Driver idDriver,Integer turn ) {
        this.id = id;
        this.turn = turn;
        this.day = day;
        this.idDriver = idDriver;
        this.idLine = idLine;
    }

    public Assignment() {
    }

    public Assignment(Line idLine, Driver idDriver, Integer turn) {
        this.idLine = idLine;
        this.idDriver = idDriver;
        this.turn = turn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Line getIdLine() {
        return idLine;
    }

    public void setIdLine(Line idLine) {
        this.idLine = idLine;
    }

    public Driver getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(Driver idDriver) {
        this.idDriver = idDriver;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public Integer getTurn() {
        return turn;
    }

    public void setTurn(Integer turn) {
        this.turn = turn;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", Line=" + idLine.getId() +
                ", Driver=" + idDriver.getName() +
                ", day=" + day +
                ", turn=" + turn +
                '}';
    }
}