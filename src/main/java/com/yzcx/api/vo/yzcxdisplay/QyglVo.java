package com.yzcx.api.vo.yzcxdisplay;

import com.yzcx.api.util.YZCXConstant;
import com.yzcx.api.vo.YZCXSearchParam;

import java.io.Serializable;

/**
 * Created by LiuDongguang on 2017/11/9.
 * 全院概览
 */
public class QyglVo implements Serializable {
    private Double putong;
    private Double jizhen;
    private Double yuyueshu;
    //////
    private YZCXSearchParam param;
    ////
    private ZyxxIndex zhuyuan;
    //
    private Long chufangshu;//处方数
    private Double pjchufang;//平均处方金额
    private Double maxchufang;//最大处方金额
    private Double minchufang;//最小处方金额
    private Double sumchufang;//处方金额总和
    private Integer ysgs;//医生个数
    private Integer jzsum;
    private Integer mzsum;
    ///
    private FeiYongHuiZong indexFeiYongZong;
    ////
    private Double yiLiao;
    private Double yao;
    private Double qiTa;
    ////
    private SsxxIndex shoushudata;

    public SsxxIndex getShoushudata() {
        return shoushudata;
    }

    public void setShoushudata(SsxxIndex shoushudata) {
        this.shoushudata = shoushudata;
    }

    public Double getYiLiao() {
        return yiLiao;
    }

    public void setYiLiao(Double yiLiao) {
        this.yiLiao = yiLiao;
    }

    public Double getYao() {
        return yao;
    }

    public void setYao(Double yao) {
        this.yao = yao;
    }

    public Double getQiTa() {
        return qiTa;
    }

    public void setQiTa(Double qiTa) {
        this.qiTa = qiTa;
    }

    public FeiYongHuiZong getIndexFeiYongZong() {
        return indexFeiYongZong;
    }

    public void setIndexFeiYongZong(FeiYongHuiZong indexFeiYongZong) {
        this.indexFeiYongZong = indexFeiYongZong;
    }

    public Double getPutong() {
        return putong;
    }

    public void setPutong(Double putong) {
        this.putong = putong;
    }

    public Double getJizhen() {
        return jizhen;
    }

    public void setJizhen(Double jizhen) {
        this.jizhen = jizhen;
    }

    public Double getYuyueshu() {
        return yuyueshu;
    }

    public void setYuyueshu(Double yuyueshu) {
        this.yuyueshu = yuyueshu;
    }

    public YZCXSearchParam getParam() {
        return param;
    }

    public void setParam(YZCXSearchParam param) {
        this.param = param;
    }

    public ZyxxIndex getZhuyuan() {
        return zhuyuan;
    }

    public void setZhuyuan(ZyxxIndex zhuyuan) {
        this.zhuyuan = zhuyuan;
    }

    public Long getChufangshu() {
        return chufangshu;
    }

    public void setChufangshu(Long chufangshu) {
        this.chufangshu = chufangshu;
    }

    public Double getPjchufang() {
        return pjchufang;
    }

    public void setPjchufang(Double pjchufang) {
        this.pjchufang = pjchufang;
    }

    public Double getMaxchufang() {
        return maxchufang;
    }

    public void setMaxchufang(Double maxchufang) {
        this.maxchufang = maxchufang;
    }

    public Double getMinchufang() {
        return minchufang;
    }

    public void setMinchufang(Double minchufang) {
        this.minchufang = minchufang;
    }

    public Double getSumchufang() {
        return sumchufang;
    }

    public void setSumchufang(Double sumchufang) {
        this.sumchufang = sumchufang;
    }

    public Integer getYsgs() {
        return ysgs;
    }

    public void setYsgs(Integer ysgs) {
        this.ysgs = ysgs;
    }

    public Integer getJzsum() {
        return jzsum;
    }

    public void setJzsum(Integer jzsum) {
        this.jzsum = jzsum;
    }

    public Integer getMzsum() {
        return mzsum;
    }

    public void setMzsum(Integer mzsum) {
        this.mzsum = mzsum;
    }

    @Override
    public String toString() {
        return "QyglVo{" +
                "putong=" + putong +
                ", jizhen=" + jizhen +
                ", yuyueshu=" + yuyueshu +
                ", param=" + param +
                ", zhuyuan=" + zhuyuan +
                ", chufangshu=" + chufangshu +
                ", pjchufang=" + pjchufang +
                ", maxchufang=" + maxchufang +
                ", minchufang=" + minchufang +
                ", sumchufang=" + sumchufang +
                ", ysgs=" + ysgs +
                ", jzsum=" + jzsum +
                ", mzsum=" + mzsum +
                ", indexFeiYongZong=" + indexFeiYongZong +
                ", yiLiao=" + yiLiao +
                ", yao=" + yao +
                ", qiTa=" + qiTa +
                '}';
    }
}
