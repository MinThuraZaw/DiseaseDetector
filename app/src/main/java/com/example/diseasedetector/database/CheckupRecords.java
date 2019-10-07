package com.example.diseasedetector.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.diseasedetector.database.AppDatabase;

@Entity
public class CheckupRecords {

        @PrimaryKey(autoGenerate = true)
        public int pid;

        @ColumnInfo(name = "attr")
        public String attribute;

        @ColumnInfo(name = "dtype")
        public String dtype;

        @ColumnInfo(name = "result")
        public String result;

        @ColumnInfo(name = "datetime")
        public String datetime;

        public CheckupRecords( String attribute, String dtype, String result, String datetime) {
                this.pid = pid;
                this.attribute = attribute;
                this.dtype = dtype;
                this.result = result;
                this.datetime = datetime;
        }

        public int getPid() {
                return pid;
        }

        public void setPid(int pid) {
                this.pid = pid;
        }

        public String getAttribute() {
                return attribute;
        }

        public void setAttribute(String attribute) {
                this.attribute = attribute;
        }

        public String getDtype() {
                return dtype;
        }

        public void setDtype(String dtype) {
                this.dtype = dtype;
        }

        public String getResult() {
                return result;
        }

        public void setResult(String result) {
                this.result = result;
        }

        public String getDatetime() {
                return datetime;
        }

        public void setDatetime(String datetime) {
                this.datetime = datetime;
        }
}
