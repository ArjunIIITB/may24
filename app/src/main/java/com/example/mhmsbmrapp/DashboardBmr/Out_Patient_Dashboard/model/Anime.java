package com.example.mhmsbmrapp.DashboardBmr.Out_Patient_Dashboard.model;

public class Anime {

        private String givenName ;
        private String middleName;
        private String email;
        private String patientName;
        private int dateOfBirth;
        private String phoneNumber;
        private String personId ;
        private String image_url;
        private String patientId;

        public Anime() {
        }

        public Anime(String givenName, String middleName, String email, String patientName, int dateOfBirth, String phoneNumber, String personId,
                     String image_url, String patientId) {
            this.givenName = givenName;
            this.middleName = middleName;
            this.email = email;
            this.patientName = patientName;
            this.dateOfBirth = dateOfBirth;
            this.phoneNumber = phoneNumber;
            this.personId = personId;
            this.image_url = image_url;
            this.patientId = patientId;
        }


        public String getGivenName() {
            return givenName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public String getPatientName() {
            return patientName;
        }

        public String getEmail() {
            return email;
        }

        public int getDateOfBirth() {
            return dateOfBirth;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getPersonId() {
            return personId;
        }

        public String getImage_url() {
            return image_url;
        }

        public String getPatientId() {
        return patientId;
    }


        public void setGivenName(String givenName) {
            this.givenName = givenName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPatientName(String patientName) {
            this.patientName = patientName;
        }

        public void setDateOfBirth(int dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public void setPersonId(String personId) {
            this.personId = personId;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    }
