package com.example.administrator.myparkingos.ui;

/**
 * Created by Administrator on 2017-04-13.
 */
public class Summary
{
    /// <summary>
    /// txbSurplusCarCount (剩余车位)
    /// </summary>
    private int SurplusCarCount ;

    /// <summary>
    /// lblMthCount  (记录场内固定车总数)
    /// </summary>
    private int MthCount ;

    /// <summary>
    /// lblTmpCount (记录场内临时车总数)
    /// </summary>
    private int TmpCount ;

    /// <summary>
    ///lblFreCount (记录场内免费车总数)
    /// </summary>
    private int FreCount ;

    /// <summary>
    /// lblStrCount（记录场内储值车总数）
    /// </summary>
    private int StrCount ;

    /// <summary>
    /// lblOutCount (记录场内派出车数)
    /// </summary>
    private int OutCount ;

    /// <summary>
    /// lblOpenCount  (手动开闸数)
    /// </summary>
    private int OpenCount ;

    /// <summary>
    /// lblFreMoney  (免费金额)
    /// </summary>
    private double FreMoney ;

    /// <summary>
    /// lblMoneyCount  (总收费金额)
    /// </summary>
    private double MoneyCount ;

    public int getSurplusCarCount()
    {
        return SurplusCarCount;
    }

    public void setSurplusCarCount(int surplusCarCount)
    {
        SurplusCarCount = surplusCarCount;
    }

    public int getMthCount()
    {
        return MthCount;
    }

    public void setMthCount(int mthCount)
    {
        MthCount = mthCount;
    }

    public int getTmpCount()
    {
        return TmpCount;
    }

    public void setTmpCount(int tmpCount)
    {
        TmpCount = tmpCount;
    }

    public int getFreCount()
    {
        return FreCount;
    }

    public void setFreCount(int freCount)
    {
        FreCount = freCount;
    }

    public int getStrCount()
    {
        return StrCount;
    }

    public void setStrCount(int strCount)
    {
        StrCount = strCount;
    }

    public int getOutCount()
    {
        return OutCount;
    }

    public void setOutCount(int outCount)
    {
        OutCount = outCount;
    }

    public int getOpenCount()
    {
        return OpenCount;
    }

    public void setOpenCount(int openCount)
    {
        OpenCount = openCount;
    }

    public double getFreMoney()
    {
        return FreMoney;
    }

    public void setFreMoney(double freMoney)
    {
        FreMoney = freMoney;
    }

    public double getMoneyCount()
    {
        return MoneyCount;
    }

    public void setMoneyCount(double moneyCount)
    {
        MoneyCount = moneyCount;
    }

    @Override
    public String toString()
    {
        return "Summary{" +
                "SurplusCarCount=" + SurplusCarCount +
                ", MthCount=" + MthCount +
                ", TmpCount=" + TmpCount +
                ", FreCount=" + FreCount +
                ", StrCount=" + StrCount +
                ", OutCount=" + OutCount +
                ", OpenCount=" + OpenCount +
                ", FreMoney=" + FreMoney +
                ", MoneyCount=" + MoneyCount +
                '}';
    }
}
