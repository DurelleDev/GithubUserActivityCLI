package org.Deisha;

public class UserData {
        private String userType;
        private String userRepoName;

        public String getUserType(){
            return userType;
        }

        public String getUserRepoName(){
            return userRepoName;
        }

        public void setUserType(String userType){
            this.userType = userType;
        }

        public void setUserRepoName(String userRepoName){
            this.userRepoName = userRepoName;
        }
}
