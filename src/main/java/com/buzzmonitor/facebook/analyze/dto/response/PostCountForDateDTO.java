
package com.buzzmonitor.facebook.analyze.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PostCountForDateDTO implements Comparable<PostCountForDateDTO> {

    @JsonFormat(
            shape = Shape.STRING,
            pattern = "yyyyMMdd")
    private LocalDate date;
    @JsonProperty("sum_posts")
    private Long sum;

    public PostCountForDateDTO() {
    }

    public PostCountForDateDTO(LocalDate date) {
        this.date = date;
    }

    public PostCountForDateDTO(int year, int month, int dayOfMonth, Long sum) {
        super();
        this.date = LocalDate.of(year, month, dayOfMonth);
        this.sum = sum;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((sum == null) ? 0 : sum.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PostCountForDateDTO other = (PostCountForDateDTO) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (sum == null) {
            if (other.sum != null)
                return false;
        } else if (!sum.equals(other.sum))
            return false;
        return true;
    }

    @Override
    public int compareTo(PostCountForDateDTO postAnother) {
        return this.getDate().compareTo(postAnother.getDate());
    }

    @Override
    public String toString() {
        return "PostCountForDateDTO [date=" + date + ", sum=" + sum + "]";
    }

}
