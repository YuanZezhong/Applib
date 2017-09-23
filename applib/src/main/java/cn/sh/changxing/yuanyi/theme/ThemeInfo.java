package cn.sh.changxing.yuanyi.theme;

/**
 * Created by yuanyi on 17-9-16.
 */

public class ThemeInfo {
    private String themeName;
    private String resNameSuffix;
    private String packageName;
    private String themePath;

    public ThemeInfo(String themeName, String themePath) {
        this.themeName = themeName;
        this.themePath = themePath;
    }

    public ThemeInfo(String themeName, String resNameSuffix, String packageName) {
        this.themeName = themeName;
        this.resNameSuffix = resNameSuffix == null? "": resNameSuffix;
        this.packageName = packageName;
    }

    public String getThemeName() {
        return themeName;
    }

    public String getResNameSuffix() {
        return resNameSuffix;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getThemePath() {
        return themePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThemeInfo themeInfo = (ThemeInfo) o;

        return themeName != null ? themeName.equals(themeInfo.themeName) : themeInfo.themeName == null;
    }

    @Override
    public int hashCode() {
        return themeName != null ? themeName.hashCode() : 0;
    }
}
