
package com.example.lab4;

import android.os.Parcel;
import android.os.Parcelable;

public class Bloc implements Parcelable {

    private String adresa;
    private int nrApartamente;
    private int nrEtaje;
    private boolean areLift;

    public Bloc(String adresa, int nrApartamente,
                int nrEtaje, boolean areLift) {
        this.adresa = adresa;
        this.nrApartamente = nrApartamente;
        this.nrEtaje = nrEtaje;
        this.areLift = areLift;
    }

    protected Bloc(Parcel in) {
        adresa = in.readString();
        nrApartamente = in.readInt();
        nrEtaje = in.readInt();
        areLift = in.readByte() != 0;
    }

    public static final Creator<Bloc> CREATOR = new Creator<Bloc>() {
        @Override
        public Bloc createFromParcel(Parcel in) {
            return new Bloc(in);
        }

        @Override
        public Bloc[] newArray(int size) {
            return new Bloc[size];
        }
    };

    public String getAdresa() {
        return adresa;
    }

    public int getNrApartamente() {
        return nrApartamente;
    }

    public int getNrEtaje() {
        return nrEtaje;
    }

    public boolean isAreLift() {
        return areLift;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setNrApartamente(int nrApartamente) {
        this.nrApartamente = nrApartamente;
    }

    public void setNrEtaje(int nrEtaje) {
        this.nrEtaje = nrEtaje;
    }

    public void setAreLift(boolean areLift) {
        this.areLift = areLift;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(adresa);
        dest.writeInt(nrApartamente);
        dest.writeInt(nrEtaje);
        dest.writeByte((byte) (areLift ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public String toString() {
        return "Adresa: " + adresa
                + "\nApartamente: " + nrApartamente
                + "\nEtaje: " + nrEtaje
                + "\nAre lift: " + (areLift ? "Da" : "Nu");
    }
}
