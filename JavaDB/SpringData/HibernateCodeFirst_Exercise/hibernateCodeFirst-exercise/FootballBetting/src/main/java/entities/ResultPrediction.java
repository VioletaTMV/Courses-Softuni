package entities;

import enums.Prediction;

import javax.persistence.*;

@Entity
@Table(name = "result_predictions")
public class ResultPrediction extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Basic
    private Prediction prediction;


    public ResultPrediction(){}


    public Prediction getPrediction() {
        return prediction;
    }

    public void setPrediction(Prediction prediction) {
        this.prediction = prediction;
    }
}
