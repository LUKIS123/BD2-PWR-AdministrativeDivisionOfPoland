package pl.edu.pwr.contract.Dtos;

import java.time.LocalDateTime;

public class ReportDto {
    public int id;
    public int voivodeshipId;
    public String voivodeshipName;
    public int countyId;
    public String countyName;
    public int communeId;
    public String communeName;
    public String topic;
    public String content;
    public LocalDateTime reportingDate;

    public ReportDto(int id, int voivodeshipId, String voivodeshipName, int countyId, String countyName, int communeId, String communeName, String topic, String content, LocalDateTime reportingDate) {
        this.id = id;
        this.voivodeshipId = voivodeshipId;
        this.voivodeshipName = voivodeshipName;
        this.countyId = countyId;
        this.countyName = countyName;
        this.communeId = communeId;
        this.communeName = communeName;
        this.topic = topic;
        this.content = content;
        this.reportingDate = reportingDate;
    }
}
