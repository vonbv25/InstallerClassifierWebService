package com.Classifier;

import java.io.Serializable;

/**
 * Created by von on 12/15/14.
 */


public class Installer implements Serializable{


    private String fileName;

    private boolean installer;


    public Installer() {
        this.fileName = "";
        this.installer = false;
    }

    public Installer(String fileName, boolean installer) {

        this.fileName = fileName;
        this.installer = installer;
    }

    public String getFileName() {
        return fileName;
    }


    public boolean isInstaller() {
        return installer;
    }

    @Override
    public String toString() {
        return "File{" +
                "fileName='" + fileName + '\'' +
                ", installer=" + installer +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Installer)) return false;

        Installer installer1 = (Installer) o;

        if (installer != installer1.installer) return false;
        if (!fileName.equals(installer1.fileName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fileName.hashCode();
        result = 31 * result + (installer ? 1 : 0);
        return result;
    }
}
