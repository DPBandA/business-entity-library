/*
Business Entity Library (BEL) - A foundational library for JSF web applications 
Copyright (C) 2017  D P Bennett & Associates Limited

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

Email: info@dpbennett.com.jm
 */
package jm.com.dpbennett.business.entity.utils;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.faces.model.SelectItem;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jm.com.dpbennett.business.entity.AccPacCustomer;
import jm.com.dpbennett.business.entity.Address;
import jm.com.dpbennett.business.entity.BusinessEntity;
import jm.com.dpbennett.business.entity.Client;
import jm.com.dpbennett.business.entity.Contact;
import jm.com.dpbennett.business.entity.DatePeriod;
import jm.com.dpbennett.business.entity.JobManagerUser;
import jm.com.dpbennett.business.entity.Person;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author dbennett
 */
public class BusinessEntityUtils {

    private static ArrayList countries;
    private static EntityManagerFactory EMF;
    public static String MONTH_NAMES[] = {
        "January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"
    };
    public static String ALPHABET[] = {
        "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
        "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    public static Date getNow() {
        return new Date();
    }

    public static int getLetterIndex(String letter) {
        for (int i = 0; i < ALPHABET.length; i++) {
            if (ALPHABET[i].equals(letter)) {
                return i;
            }
        }

        return -1;
    }

    public static Boolean isDateWithinPeriod(Date dateToCheck, Date startDate, Date endDate) {
        if ((dateToCheck.compareTo(startDate) >= 0) && (dateToCheck.compareTo(endDate) <= 0)) {
            return true;
        } else {
            return false;
        }
    }

    public static String getMonthAndYearString(Date date) {
        String dateMonthAndYearString = "";
        Calendar c = Calendar.getInstance();

        c.setTime(date);
        dateMonthAndYearString = MONTH_NAMES[c.get(Calendar.MONTH)];
        dateMonthAndYearString = dateMonthAndYearString + " " + getYearFromDate(date);

        return dateMonthAndYearString;
    }

    public static String getBasicAddress(Address address) {
        String addressStr = "";

        addressStr = getValidString(address.getAddressLine1()) + "\n"
                + getValidString(address.getAddressLine2()) + "\n"
                + getValidString(address.getStateOrProvince()) + "\n"
                + getValidString(address.getCity()) + "\n";

        return addressStr;
    }

    public static void insertColumnHeaders(HSSFWorkbook wb,
            HSSFSheet sheet,
            short rowIndex,
            String[] headers,
            short fontSize) {
        // Setup column headers
        // Create column header font
        HSSFFont columnHeaderFont = wb.createFont();
        columnHeaderFont.setFontHeightInPoints(fontSize);
        columnHeaderFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        HSSFRow columnHeaderRow = sheet.createRow(fontSize);
        // Setup header style
        HSSFCellStyle headerColumnCellStyle = wb.createCellStyle();
        headerColumnCellStyle.setFont(columnHeaderFont);
        headerColumnCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        for (int i = 0; i < headers.length; i++) {
            columnHeaderRow.createCell(i).setCellValue(new HSSFRichTextString(headers[i]));
            columnHeaderRow.getCell(i).setCellStyle(headerColumnCellStyle);
        }
    }

    public static void insertExcelHeader(HSSFWorkbook wb,
            HSSFSheet sheet,
            String header,
            short rowIndex,
            short columnIndex,
            int widthInColumns,
            short fontSize) {
        // Create header font
        HSSFFont headerFont = wb.createFont();
        headerFont.setFontHeightInPoints(fontSize);
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        HSSFRow headerRow = sheet.createRow(rowIndex);
        // Setup header style
        HSSFCellStyle headerCellStyle = wb.createCellStyle();
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        headerCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        for (int i = 0; i < widthInColumns; i++) {
            headerRow.createCell(i);
            // Set cell style
            headerRow.getCell(i).setCellStyle(headerCellStyle);
            // Set as first sheet
        }
        // merge header cells
        sheet.addMergedRegion(new CellRangeAddress(
                rowIndex, //first row
                rowIndex, //last row
                0, //first column
                widthInColumns - 1 //last column
        ));
        // Set header title
        headerRow.getCell(0).setCellValue(new HSSFRichTextString(header));
    }

    public static String getValidString(String str) {
        if (str == null) {
            return "";
        } else {
            return str.trim();
        }
    }

    public static List getPersonalTitles() {
        ArrayList titles = new ArrayList();

        titles.add(new SelectItem("--", "--"));
        titles.add(new SelectItem("Mr.", "Mr."));
        titles.add(new SelectItem("Ms.", "Ms."));
        titles.add(new SelectItem("Mrs.", "Mrs."));
        titles.add(new SelectItem("Miss", "Miss"));
        titles.add(new SelectItem("Dr.", "Dr."));

        return titles;
    }

    public static List getSexes() {
        ArrayList titles = new ArrayList();

        titles.add(new SelectItem("--", "--"));
        titles.add(new SelectItem("Male", "Male"));
        titles.add(new SelectItem("Female", "Female"));

        return titles;
    }

    // Not used. Thes are obtained from database. May be removed in future.
    public static List getCountries() {
        countries = new ArrayList();

        countries.add(new SelectItem("af", "Afghanistan"));
        countries.add(new SelectItem("ax", "Aland Islands"));
        countries.add(new SelectItem("al", "Albania"));
        countries.add(new SelectItem("dz", "Algeria"));
        countries.add(new SelectItem("as", "American Samoa"));
        countries.add(new SelectItem("ad", "Andorra"));
        countries.add(new SelectItem("ao", "Angola"));
        countries.add(new SelectItem("ai", "Anguilla"));
        countries.add(new SelectItem("aq", "Antarctica"));
        countries.add(new SelectItem("ag", "Antigua and Barbuda"));
        countries.add(new SelectItem("ar", "Argentina"));
        countries.add(new SelectItem("am", "Armenia"));
        countries.add(new SelectItem("aw", "Aruba"));
        countries.add(new SelectItem("au", "Australia"));
        countries.add(new SelectItem("at", "Austria"));
        countries.add(new SelectItem("az", "Azerbaijan"));
        countries.add(new SelectItem("bs", "Bahamas"));
        countries.add(new SelectItem("bh", "Bahrain"));
        countries.add(new SelectItem("bd", "Bangladesh"));
        countries.add(new SelectItem("bb", "Barbados"));
        countries.add(new SelectItem("by", "Belarus"));
        countries.add(new SelectItem("be", "Belgium"));
        countries.add(new SelectItem("bz", "Belize"));
        countries.add(new SelectItem("bj", "Benin"));
        countries.add(new SelectItem("bm", "Bermuda"));
        countries.add(new SelectItem("bt", "Bhutan"));
        countries.add(new SelectItem("bo", "Bolivia"));
        countries.add(new SelectItem("ba", "Bosnia and Herzegovina"));
        countries.add(new SelectItem("bw", "Botswana"));
        countries.add(new SelectItem("bv", "Bouvet Island"));
        countries.add(new SelectItem("br", "Brazil"));
        countries.add(new SelectItem("io", "British Indian Ocean Territory"));
        countries.add(new SelectItem("vg", "British Virgin Islands"));
        countries.add(new SelectItem("bn", "Brunei"));
        countries.add(new SelectItem("bg", "Bulgaria"));
        countries.add(new SelectItem("bf", "Burkina Faso"));
        countries.add(new SelectItem("bi", "Burundi"));
        countries.add(new SelectItem("kh", "Cambodia"));
        countries.add(new SelectItem("cm", "Cameroon"));
        countries.add(new SelectItem("ca", "Canada"));
        countries.add(new SelectItem("cv", "Cape Verde"));
        countries.add(new SelectItem("ky", "Cayman Islands"));
        countries.add(new SelectItem("cf", "Central African Republic"));
        countries.add(new SelectItem("td", "Chad"));
        countries.add(new SelectItem("cl", "Chile"));
        countries.add(new SelectItem("cn", "China"));
        countries.add(new SelectItem("cx", "Christmas Island"));
        countries.add(new SelectItem("cc", "Cocos (Keeling) Islands"));
        countries.add(new SelectItem("co", "Colombia"));
        countries.add(new SelectItem("km", "Comoros"));
        countries.add(new SelectItem("cg", "Congo"));
        countries.add(new SelectItem("ck", "Cook Islands"));
        countries.add(new SelectItem("cr", "Costa Rica"));
        countries.add(new SelectItem("hr", "Croatia"));
        countries.add(new SelectItem("cu", "Cuba"));
        countries.add(new SelectItem("cy", "Cyprus"));
        countries.add(new SelectItem("cz", "Czech Republic"));
        countries.add(new SelectItem("cd", "Democratic Republic of Congo"));
        countries.add(new SelectItem("dk", "Denmark"));
        countries.add(new SelectItem("xx", "Disputed Territory"));
        countries.add(new SelectItem("dj", "Djibouti"));
        countries.add(new SelectItem("dm", "Dominica"));
        countries.add(new SelectItem("do", "Dominican Republic"));
        countries.add(new SelectItem("tl", "East Timor"));
        countries.add(new SelectItem("ec", "Ecuador"));
        countries.add(new SelectItem("eg", "Egypt"));
        countries.add(new SelectItem("sv", "El Salvador"));
        countries.add(new SelectItem("gq", "Equatorial Guinea"));
        countries.add(new SelectItem("er", "Eritrea"));
        countries.add(new SelectItem("ee", "Estonia"));
        countries.add(new SelectItem("et", "Ethiopia"));
        countries.add(new SelectItem("fk", "Falkland Islands"));
        countries.add(new SelectItem("fo", "Faroe Islands"));
        countries.add(new SelectItem("fm", "Federated States of Micronesia"));
        countries.add(new SelectItem("fj", "Fiji"));
        countries.add(new SelectItem("fi", "Finland"));
        countries.add(new SelectItem("fr", "France"));
        countries.add(new SelectItem("gf", "French Guyana"));
        countries.add(new SelectItem("pf", "French Polynesia"));
        countries.add(new SelectItem("tf", "French Southern Territories"));
        countries.add(new SelectItem("ga", "Gabon"));
        countries.add(new SelectItem("gm", "Gambia"));
        countries.add(new SelectItem("ge", "Georgia"));
        countries.add(new SelectItem("de", "Germany"));
        countries.add(new SelectItem("gh", "Ghana"));
        countries.add(new SelectItem("gi", "Gibraltar"));
        countries.add(new SelectItem("gr", "Greece"));
        countries.add(new SelectItem("gl", "Greenland"));
        countries.add(new SelectItem("gd", "Grenada"));
        countries.add(new SelectItem("gp", "Guadeloupe"));
        countries.add(new SelectItem("gu", "Guam"));
        countries.add(new SelectItem("gt", "Guatemala"));
        countries.add(new SelectItem("gn", "Guinea"));
        countries.add(new SelectItem("gw", "Guinea-Bissau"));
        countries.add(new SelectItem("gy", "Guyana"));
        countries.add(new SelectItem("ht", "Haiti"));
        countries.add(new SelectItem("hm", "Heard Island and Mcdonald Islands"));
        countries.add(new SelectItem("hn", "Honduras"));
        countries.add(new SelectItem("hk", "Hong Kong"));
        countries.add(new SelectItem("hu", "Hungary"));
        countries.add(new SelectItem("is", "Iceland"));
        countries.add(new SelectItem("in", "India"));
        countries.add(new SelectItem("id", "Indonesia"));
        countries.add(new SelectItem("ir", "Iran"));
        countries.add(new SelectItem("iq", "Iraq"));
        countries.add(new SelectItem("xe", "Iraq-Saudi Arabia Neutral Zone"));
        countries.add(new SelectItem("ie", "Ireland"));
        countries.add(new SelectItem("il", "Israel"));
        countries.add(new SelectItem("it", "Italy"));
        countries.add(new SelectItem("ci", "Ivory Coast"));
        countries.add(new SelectItem("jm", "Jamaica"));
        countries.add(new SelectItem("jp", "Japan"));
        countries.add(new SelectItem("jo", "Jordan"));
        countries.add(new SelectItem("kz", "Kazakhstan"));
        countries.add(new SelectItem("ke", "Kenya"));
        countries.add(new SelectItem("ki", "Kiribati"));
        countries.add(new SelectItem("kw", "Kuwait"));
        countries.add(new SelectItem("kg", "Kyrgyzstan"));
        countries.add(new SelectItem("la", "Laos"));
        countries.add(new SelectItem("lv", "Latvia"));
        countries.add(new SelectItem("lb", "Lebanon"));
        countries.add(new SelectItem("ls", "Lesotho"));
        countries.add(new SelectItem("lr", "Liberia"));
        countries.add(new SelectItem("ly", "Libya"));
        countries.add(new SelectItem("li", "Liechtenstein"));
        countries.add(new SelectItem("lt", "Lithuania"));
        countries.add(new SelectItem("lu", "Luxembourg"));
        countries.add(new SelectItem("mo", "Macau"));
        countries.add(new SelectItem("mk", "Macedonia"));
        countries.add(new SelectItem("mg", "Madagascar"));
        countries.add(new SelectItem("mw", "Malawi"));
        countries.add(new SelectItem("my", "Malaysia"));
        countries.add(new SelectItem("mv", "Maldives"));
        countries.add(new SelectItem("ml", "Mali"));
        countries.add(new SelectItem("mt", "Malta"));
        countries.add(new SelectItem("mh", "Marshall Islands"));
        countries.add(new SelectItem("mq", "Martinique"));
        countries.add(new SelectItem("mr", "Mauritania"));
        countries.add(new SelectItem("mu", "Mauritius"));
        countries.add(new SelectItem("yt", "Mayotte"));
        countries.add(new SelectItem("mx", "Mexico"));
        countries.add(new SelectItem("md", "Moldova"));
        countries.add(new SelectItem("mc", "Monaco"));
        countries.add(new SelectItem("mn", "Mongolia"));
        countries.add(new SelectItem("ms", "Montserrat"));
        countries.add(new SelectItem("ma", "Morocco"));
        countries.add(new SelectItem("mz", "Mozambique"));
        countries.add(new SelectItem("mm", "Myanmar"));
        countries.add(new SelectItem("na", "Namibia"));
        countries.add(new SelectItem("nr", "Nauru"));
        countries.add(new SelectItem("np", "Nepal"));
        countries.add(new SelectItem("nl", "Netherlands"));
        countries.add(new SelectItem("an", "Netherlands Antilles"));
        countries.add(new SelectItem("nc", "New Caledonia"));
        countries.add(new SelectItem("nz", "New Zealand"));
        countries.add(new SelectItem("ni", "Nicaragua"));
        countries.add(new SelectItem("ne", "Niger"));
        countries.add(new SelectItem("ng", "Nigeria"));
        countries.add(new SelectItem("nu", "Niue"));
        countries.add(new SelectItem("nf", "Norfolk Island"));
        countries.add(new SelectItem("kp", "North Korea"));
        countries.add(new SelectItem("mp", "Northern Mariana Islands"));
        countries.add(new SelectItem("no", "Norway"));
        countries.add(new SelectItem("om", "Oman"));
        countries.add(new SelectItem("pk", "Pakistan"));
        countries.add(new SelectItem("pw", "Palau"));
        countries.add(new SelectItem("ps", "Palestinian Territories"));
        countries.add(new SelectItem("pa", "Panama"));
        countries.add(new SelectItem("pg", "Papua New Guinea"));
        countries.add(new SelectItem("py", "Paraguay"));
        countries.add(new SelectItem("pe", "Peru"));
        countries.add(new SelectItem("ph", "Philippines"));
        countries.add(new SelectItem("pn", "Pitcairn Islands"));
        countries.add(new SelectItem("pl", "Poland"));
        countries.add(new SelectItem("pt", "Portugal"));
        countries.add(new SelectItem("pr", "Puerto Rico"));
        countries.add(new SelectItem("qa", "Qatar"));
        countries.add(new SelectItem("re", "Reunion"));
        countries.add(new SelectItem("ro", "Romania"));
        countries.add(new SelectItem("ru", "Russia"));
        countries.add(new SelectItem("rw", "Rwanda"));
        countries.add(new SelectItem("sh", "Saint Helena and Dependencies"));
        countries.add(new SelectItem("kn", "Saint Kitts and Nevis"));
        countries.add(new SelectItem("lc", "Saint Lucia"));
        countries.add(new SelectItem("pm", "Saint Pierre and Miquelon"));
        countries.add(new SelectItem("vc", "Saint Vincent and the Grenadines"));
        countries.add(new SelectItem("ws", "Samoa"));
        countries.add(new SelectItem("sm", "San Marino"));
        countries.add(new SelectItem("st", "Sao Tome and Principe"));
        countries.add(new SelectItem("sa", "Saudi Arabia"));
        countries.add(new SelectItem("sn", "Senegal"));
        countries.add(new SelectItem("sc", "Seychelles"));
        countries.add(new SelectItem("sl", "Sierra Leone"));
        countries.add(new SelectItem("sg", "Singapore"));
        countries.add(new SelectItem("sk", "Slovakia"));
        countries.add(new SelectItem("si", "Slovenia"));
        countries.add(new SelectItem("sb", "Solomon Islands"));
        countries.add(new SelectItem("so", "Somalia"));
        countries.add(new SelectItem("za", "South Africa"));
        countries.add(new SelectItem("gs", "South Georgia and South Sandwich Islands"));
        countries.add(new SelectItem("kr", "South Korea"));
        countries.add(new SelectItem("es", "Spain"));
        countries.add(new SelectItem("pi", "Spratly Islands"));
        countries.add(new SelectItem("lk", "Sri Lanka"));
        countries.add(new SelectItem("sd", "Sudan"));
        countries.add(new SelectItem("sr", "Suriname"));
        countries.add(new SelectItem("sj", "Svalbard and Jan Mayen"));
        countries.add(new SelectItem("sz", "Swaziland"));
        countries.add(new SelectItem("se", "Sweden"));
        countries.add(new SelectItem("ch", "Switzerland"));
        countries.add(new SelectItem("sy", "Syria"));
        countries.add(new SelectItem("tw", "Taiwan"));
        countries.add(new SelectItem("tj", "Tajikistan"));
        countries.add(new SelectItem("tz", "Tanzania"));
        countries.add(new SelectItem("th", "Thailand"));
        countries.add(new SelectItem("tg", "Togo"));
        countries.add(new SelectItem("tk", "Tokelau"));
        countries.add(new SelectItem("to", "Tonga"));
        countries.add(new SelectItem("tt", "Trinidad and Tobago"));
        countries.add(new SelectItem("tn", "Tunisia"));
        countries.add(new SelectItem("tr", "Turkey"));
        countries.add(new SelectItem("tm", "Turkmenistan"));
        countries.add(new SelectItem("tc", "Turks And Caicos Islands"));
        countries.add(new SelectItem("tv", "Tuvalu"));
        countries.add(new SelectItem("ug", "Uganda"));
        countries.add(new SelectItem("ua", "Ukraine"));
        countries.add(new SelectItem("ae", "United Arab Emirates"));
        countries.add(new SelectItem("uk", "United Kingdom"));
        countries.add(new SelectItem("us", "United States"));
        countries.add(new SelectItem("um", "United States Minor Outlying Islands"));
        countries.add(new SelectItem("uy", "Uruguay"));
        countries.add(new SelectItem("vi", "US Virgin Islands"));
        countries.add(new SelectItem("uz", "Uzbekistan"));
        countries.add(new SelectItem("vu", "Vanuatu"));
        countries.add(new SelectItem("va", "Vatican City"));
        countries.add(new SelectItem("ve", "Venezuela"));
        countries.add(new SelectItem("vn", "Vietnam"));
        countries.add(new SelectItem("wf", "Wallis and Futuna"));
        countries.add(new SelectItem("eh", "Western Sahara"));
        countries.add(new SelectItem("ye", "Yemen"));
        countries.add(new SelectItem("zm", "Zambia"));
        countries.add(new SelectItem("zw", "Zimbabwe"));
        countries.add(new SelectItem("rs", "Serbia"));
        countries.add(new SelectItem("me", "Montenegro"));

        return countries;
    }

    public static List getSearchTypes() {
        ArrayList searchTypes = new ArrayList();

        searchTypes.add(new SelectItem("General", "General"));

        return searchTypes;
    }

    public List getYears() {
        List years = new ArrayList();

        Integer currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (Integer i = currentYear; i > (currentYear - 10); i--) {
            years.add(new SelectItem(i, i.toString()));
        }

        return years;
    }

    public static int characterCount(String str, char chr) {
        int count = 0;

        char chars[] = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == chr) {
                count++;
            }
        }

        return count;

    }

    public static boolean containsChar(String str, char chr) {

        char chars[] = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == chr) {
                return true;
            }
        }

        return false;
    }

    public static boolean validateName(String name) {

        if (name == null) {
            return false;
        } else if (name.equals("")) {
            return false;
        } else if (containsChar(name, '"')) {
            return false;
        } else if (name.contains("''")) {
            return false;
        } else if (name.contains(";")) {
            return false;
        }

        return true;
    }

    public static boolean validateText(String name) {

        if (name == null) {
            return false;
        } else if (name.equals("")) {
            return false;
        } else if (containsChar(name, '"')) {
            return false;
        } else if (name.contains("''")) {
            return false;
        }

        return true;
    }

    public static boolean validateAddressLine(String name) {

        if (name == null) {
            return false;
        } else if (name.equals("")) {
            return false;
        } else if (containsChar(name, '"')) {
            return false;
        } else if (name.contains("''")) {
            return false;
        } else if (name.contains(";")) {
            return false;
        }

        return true;
    }

    public static boolean validateNameOfBusinessEntity(BusinessEntity entity) {

        if (entity.getName() == null) {
            return false;
        } else if (entity.getName().equals("")) {
            return false;
        } else if (containsChar(entity.getName(), '"')) {
            return false;
        } else if (entity.getName().contains("''")) {
            return false;
        }

        return true;
    }

    public static HSSFCellStyle getExcelCellStyle(HSSFWorkbook wb, String cellType) {
        HSSFCellStyle style = null;

        if (cellType.equals("java.lang.Long")) {
            style = wb.createCellStyle();
            style.setDataFormat(wb.createDataFormat().getFormat("###"));
        } else if (cellType.equals("java.lang.Integer")) {
            style = wb.createCellStyle();
            style.setDataFormat(wb.createDataFormat().getFormat("###"));
        } else if (cellType.equals("java.lang.Double")) {
            style = wb.createCellStyle();
            style.setDataFormat(wb.createDataFormat().getFormat("#,##0.00"));
        } else if (cellType.equals("java.lang.Boolean")) {
            style = wb.createCellStyle();
        } else if (cellType.equals("java.lang.String")) {
            style = wb.createCellStyle();
        } else if (cellType.equals("java.util.Date")) {
            style = wb.createCellStyle();
            style.setDataFormat(wb.createDataFormat().getFormat("MMM dd, yyyy"));
        }

        return style;
    }

    public static HSSFCellStyle setExcelCellDataFormat(HSSFWorkbook wb, String cellType, HSSFCellStyle style) {

        if (cellType.equals("java.lang.Long")) {
            if (style == null) {
                style = wb.createCellStyle();
            }
            style.setDataFormat(wb.createDataFormat().getFormat("###"));
        } else if (cellType.equals("java.lang.Integer")) {
            if (style == null) {
                style = wb.createCellStyle();
            }
            style.setDataFormat(wb.createDataFormat().getFormat("###"));
        } else if (cellType.equals("java.lang.Double")) {
            if (style == null) {
                style = wb.createCellStyle();
            }
            style.setDataFormat(wb.createDataFormat().getFormat("#,##0.00"));
        } else if (cellType.equals("Currency")) {
            if (style == null) {
                style = wb.createCellStyle();
            }
            style.setDataFormat(wb.createDataFormat().getFormat("J$#,##0.00"));
        } else if (cellType.equals("java.lang.Boolean")) {
            //style = wb.createCellStyle();
        } else if (cellType.equals("java.lang.String")) {
            //style = wb.createCellStyle();
        } else if (cellType.equals("java.util.Date")) {
            if (style == null) {
                style = wb.createCellStyle();
            }
            style.setDataFormat(wb.createDataFormat().getFormat("MMM dd, yyyy"));
        }

        return style;
    }

    public static XSSFCellStyle setExcelCellDataFormat(XSSFWorkbook wb, String cellType, XSSFCellStyle style) {

        if (cellType.equals("java.lang.Long")) {
            if (style == null) {
                style = wb.createCellStyle();
            }
            style.setDataFormat(wb.createDataFormat().getFormat("###"));
        } else if (cellType.equals("java.lang.Integer")) {
            if (style == null) {
                style = wb.createCellStyle();
            }
            style.setDataFormat(wb.createDataFormat().getFormat("###"));
        } else if (cellType.equals("java.lang.Double")) {
            if (style == null) {
                style = wb.createCellStyle();
            }
            style.setDataFormat(wb.createDataFormat().getFormat("#,##0.00"));
        } else if (cellType.equals("Currency")) {
            if (style == null) {
                style = wb.createCellStyle();
            }
            style.setDataFormat(wb.createDataFormat().getFormat("J$#,##0.00"));
        } else if (cellType.equals("java.lang.Boolean")) {
            //style = wb.createCellStyle();
        } else if (cellType.equals("java.lang.String")) {
            //style = wb.createCellStyle();
        } else if (cellType.equals("java.util.Date")) {
            if (style == null) {
                style = wb.createCellStyle();
            }
            style.setDataFormat(wb.createDataFormat().getFormat("MMM dd, yyyy"));
        }

        return style;
    }

    public static HSSFFont createBoldFont(HSSFWorkbook wb, short size, short color) {
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints(size);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(color);

        return font;
    }

    public static XSSFFont createBoldFont(XSSFWorkbook wb, short size, short color) {
        XSSFFont font = wb.createFont();
        font.setFontHeightInPoints(size);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(color);

        return font;
    }

    public static Boolean isBasicDataType(String type) {

        if (type.equals("java.lang.Long")) {
            return Boolean.TRUE;
        } else if (type.equals("java.lang.Integer")) {
            return Boolean.TRUE;
        } else if (type.equals("java.lang.Double")) {
            return Boolean.TRUE;
        } else if (type.equals("java.lang.Boolean")) {
            return Boolean.TRUE;
        } else if (type.equals("java.lang.String")) {
            return Boolean.TRUE;
        } else if (type.equals("java.util.Date")) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    public static void setExcelCellValue(HSSFWorkbook wb, HSSFCell cell, Object entity, String methodPath) {

        String dataType = null;
        HSSFCellStyle style = null;
        Object value = getBusinessEntityValue(entity, methodPath);

        if (value != null) {
            dataType = value.getClass().getCanonicalName();
            style = getExcelCellStyle(wb, dataType);

            if (dataType.equals("java.lang.Long")) {
                cell.setCellValue((Long) value);
            } else if (dataType.equals("java.lang.Integer")) {
                cell.setCellValue((Integer) value);
            } else if (dataType.equals("java.lang.Double")) {
                cell.setCellValue((Double) value);
            } else if (dataType.equals("java.lang.Boolean")) {
                cell.setCellValue((Boolean) value);
            } else if (dataType.equals("java.lang.String")) {
                cell.setCellValue(new HSSFRichTextString(value.toString()));
            } else if (dataType.equals("java.util.Date")) {
                cell.setCellValue((Date) value);
            }

            // set the style if it is valid
            if (style != null) {
                cell.setCellStyle(style);
            }
        }
    }

    public static void setExcelCellValue(HSSFWorkbook wb, HSSFCell cell, Object value, String dataType, HSSFCellStyle style) {

        setExcelCellDataFormat(wb, dataType, style);

        try {
            if (value != null) {
                if (dataType.equals("java.lang.Long")) {
                    cell.setCellValue((Long) value);
                } else if (dataType.equals("java.lang.Integer")) {
                    cell.setCellValue((Integer) value);
                } else if (dataType.equals("java.lang.Double")) {
                    cell.setCellValue((Double) value);
                } else if (dataType.equals("java.lang.Boolean")) {
                    cell.setCellValue((Boolean) value);
                } else if (dataType.equals("java.lang.String")) {
                    cell.setCellValue(new HSSFRichTextString(value.toString()));
                } else if (dataType.equals("java.util.Date")) {
                    cell.setCellValue((Date) value);
                }

                if (style != null) {
                    cell.setCellStyle(style);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void setExcelCellValue(HSSFWorkbook wb, HSSFSheet sheet, int rowIndex, int cellIndex,
            Object value, String dataType, HSSFCellStyle style) {

        HSSFRow row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        HSSFCell cell = row.getCell(cellIndex);
        if (cell == null) {
            cell = row.createCell(cellIndex);
        }
        HSSFCellStyle newStyle = setExcelCellDataFormat(wb, dataType, style);

        try {
            if (value != null) {
                if (dataType.equals("java.lang.Long")) {
                    cell.setCellValue((Long) value);
                } else if (dataType.equals("java.lang.Integer")) {
                    cell.setCellValue((Integer) value);
                } else if (dataType.equals("java.lang.Double")) {
                    cell.setCellValue((Double) value);
                } else if (dataType.equals("Currency")) {
                    cell.setCellValue((Double) value);
                } else if (dataType.equals("java.lang.Boolean")) {
                    cell.setCellValue((Boolean) value);
                } else if (dataType.equals("java.lang.String")) {
                    cell.setCellValue(new HSSFRichTextString(value.toString()));
                } else if (dataType.equals("java.util.Date")) {
                    cell.setCellValue((Date) value);
                }

                cell.setCellStyle(newStyle);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void setExcelCellValue(XSSFWorkbook wb, XSSFSheet sheet, int rowIndex, int cellIndex,
            Object value, String dataType, XSSFCellStyle style) {

        XSSFRow row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        XSSFCell cell = row.getCell(cellIndex);
        if (cell == null) {
            cell = row.createCell(cellIndex);
        }

        XSSFCellStyle newStyle;
        if (style != null) {
            newStyle = setExcelCellDataFormat(wb, dataType, style);
        } else {
            newStyle = cell.getCellStyle();
        }

        try {
            if (value != null) {
                if (dataType.equals("java.lang.Long")) {
                    cell.setCellValue((Long) value);
                } else if (dataType.equals("java.lang.Integer")) {
                    cell.setCellValue((Integer) value);
                } else if (dataType.equals("java.lang.Double")) {
                    cell.setCellValue((Double) value);
                } else if (dataType.equals("Currency")) {
                    cell.setCellValue((Double) value);
                } else if (dataType.equals("java.lang.Boolean")) {
                    cell.setCellValue((Boolean) value);
                } else if (dataType.equals("java.lang.String")) {
                    cell.setCellValue(new XSSFRichTextString(value.toString()));
                } else if (dataType.equals("java.util.Date")) {
                    cell.setCellValue((Date) value);
                }

                cell.setCellStyle(newStyle);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Returns a method that is of known basic type given a string representing
     * a path to a method that will return a value for use in table of values
     * for example.
     *
     * @param methodPath
     * @return
     */
    public static Method getMethodOfKnownDataType(String methodPath) {
        int i = 0;
        Method method = null;

        try {

            String[] methodNames = methodPath.split("/");
            // first class that could contain the method
            Class c = Class.forName(methodNames[0]);
            // get array of method names
            String[] methodName = methodNames[1].split("\\.");

            do {
                // get method with basic datatype and call it if possible
                method = c.getMethod(methodName[i], (Class[]) null);
                String dataType = method.getReturnType().getName();
                if (isBasicDataType(dataType)) {
                    break;
                } else {
                    // get and use the class returned by this method
                    c = method.getReturnType();
                }
                i++;

            } while (i < methodName.length);

            return method;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    /**
     * Returns the class to which a method belongs given the the methods path
     * for example.
     *
     * @param methodPath
     * @return
     */
    public static Class getClass(String methodPath) {
        int i = 0;
        Method method = null;
        Class c = null;

        try {

            String[] methodNames = methodPath.split("/");
            // first class that could contain the method
            c = Class.forName(methodNames[0]);
            // get array of method names
            String[] methodName = methodNames[1].split("\\.");

            do {
                // get method with basic datatype and call it if possible
                method = c.getMethod(methodName[i], (Class[]) null);
                String dataType = method.getReturnType().getName();
                if (isBasicDataType(dataType)) {
                    return c;
                } else {
                    // get and use the class returned by this method
                    c = method.getReturnType();
                }
                i++;

            } while (i < methodName.length);

            return c;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public static Object getBusinessEntityValue(Object entity, String methodPath) {

        Object value = null;
        Class c = null;
        Method m = null;

        // split the path into class and methods string
        String[] path = methodPath.split("/");
        // get array of method names
        String[] methodNames = path[1].split("\\.");

        int i = -1;
        try {
            do {
                ++i;
                c = entity.getClass();
                m = c.getMethod(methodNames[i], (Class[]) null);
                entity = m.invoke(c.cast(entity), (Object[]) null);
                value = entity;
            } while (!isBasicDataType(m.getReturnType().getName()));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return value;
    }

    /**
     * Creates a new client object given an AccPac client object
     *
     * @param apcustomer
     * @return
     */
    public static Client createClient(AccPacCustomer apcustomer) {
        Client client = new Client();

        return client;
    }

    public static Long saveBusinessEntity(EntityManager em, BusinessEntity businessEntity) {

        try {
            if (businessEntity.getId() != null) {
                em.merge(businessEntity);
            } else {
                em.persist(businessEntity);
            }

            return businessEntity.getId();
        } catch (Exception e) {
            System.out.println(e);

            return null;
        }

    }

    public static Date createDate(int year, int monthIndex, int day) {
        Calendar c;

        // current time and date
        c = Calendar.getInstance();
        // set date
        c.set(year, monthIndex, day, 0, 0, 0);

        return c.getTime();
    }

    public static Date createDate(Date date) {
        Calendar c;

        // current time and date
        c = Calendar.getInstance();
        c.setTime(date);
        // set date
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static int getYearFromDate(Date date) {
        Calendar c;

        // current time and date
        c = Calendar.getInstance();
        c.setTime(date);

        return c.get(Calendar.YEAR);
    }

    public static Date getStartOfCurrentYear() {
        Calendar c, current;

        // current time and date
        current = Calendar.getInstance();
        c = Calendar.getInstance();
        // set start date
        c.set(current.get(Calendar.YEAR), Calendar.JANUARY, 1, 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date getEndOfCurrentYear() {
        Calendar c, current;

        // current time and date
        current = Calendar.getInstance();
        c = Calendar.getInstance();
        // set end date
        c.set(current.get(Calendar.YEAR), Calendar.DECEMBER, 31, 23, 59, 59);
        c.set(Calendar.MILLISECOND, 999);

        return c.getTime();
    }

    public static Date getStartOfCurrentMonth() {
        Calendar c, current;

        // current time and date
        current = Calendar.getInstance();
        c = Calendar.getInstance();
        // set start date
        c.set(current.get(Calendar.YEAR), current.get(Calendar.MONTH), 1, 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date getStartOfCurrentMonthPreviousYear() {
        Calendar c, current;

        // current time and date
        current = Calendar.getInstance();
        c = Calendar.getInstance();
        // set start date
        c.set(current.get(Calendar.YEAR) - 1, current.get(Calendar.MONTH), 1, 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date getStartOfMonthInCurrentYear(int monthIndex) {
        Calendar c, current;

        // current time and date
        current = Calendar.getInstance();
        c = Calendar.getInstance();
        // set start date
        c.set(current.get(Calendar.YEAR), monthIndex, 1, 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date getEndOfCurrentMonth() {
        Calendar c, current;

        // current time and date
        current = Calendar.getInstance();
        c = Calendar.getInstance();
        // set end date
        c.set(current.get(Calendar.YEAR), current.get(Calendar.MONTH), getDaysInMonth(current.get(Calendar.MONTH)), 23, 59, 59);
        c.set(Calendar.MILLISECOND, 999);

        return c.getTime();
    }

    public static Date getEndOfCurrentMonthPreviousYear() {
        Calendar c, current;

        // current time and date
        current = Calendar.getInstance();
        c = Calendar.getInstance();
        // set end date
        c.set(current.get(Calendar.YEAR) - 1, current.get(Calendar.MONTH), getDaysInMonth(current.get(Calendar.MONTH)), 23, 59, 59);
        c.set(Calendar.MILLISECOND, 999);

        return c.getTime();
    }

    public static Date getThisDatePreviousYear() {
        Calendar c, current;

        // current time and date
        current = Calendar.getInstance();
        c = Calendar.getInstance();
        // set end date
        c.set(current.get(Calendar.YEAR) - 1, current.get(Calendar.MONTH), getDaysInMonth(current.get(Calendar.MONTH)), 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date getEndOfMonthInCurrentYear(int monthIndex) {
        Calendar c, current;

        // current time and date
        current = Calendar.getInstance();
        c = Calendar.getInstance();
        // set end date
        c.set(current.get(Calendar.YEAR), monthIndex, getDaysInMonth(monthIndex), 23, 59, 59);
        c.set(Calendar.MILLISECOND, 999);

        return c.getTime();
    }

    /**
     * Get the number of days in a month using the current year
     *
     * @param month
     * @return
     */
    public static int getDaysInMonth(int month) {
        Calendar current = Calendar.getInstance();

        switch (month) {
            case Calendar.JANUARY:
                return 31;
            case Calendar.FEBRUARY:
                int currentYear = current.get(Calendar.YEAR);
                if ((currentYear % 4) == 0) { // leap year?
                    return 29;
                } else {
                    return 28;
                }
            case Calendar.MARCH:
                return 31;
            case Calendar.APRIL:
                return 30;
            case Calendar.MAY:
                return 31;
            case Calendar.JUNE:
                return 30;
            case Calendar.JULY:
                return 31;
            case Calendar.AUGUST:
                return 31;
            case Calendar.SEPTEMBER:
                return 30;
            case Calendar.OCTOBER:
                return 31;
            case Calendar.NOVEMBER:
                return 30;
            case Calendar.DECEMBER:
                return 31;
            default:
                return -1;
        }
    }

    /**
     * getFourDigitNumberString Pad number with leading zeros if the number is
     * less than four digits
     *
     * @param number
     * @return
     */
    public static String getFourDigitString(long number) {
        String fourDigitString = "";

        if ((number >= 0L) && (number <= 9L)) {
            fourDigitString = "000" + number;
        }
        if ((number >= 10L) && (number <= 99L)) {
            fourDigitString = "00" + number;
        }
        if ((number >= 100L) && (number <= 999L)) {
            fourDigitString = "0" + number;
        }
        if (number >= 1000L) {
            fourDigitString = "" + number;
        }
        return fourDigitString;
    }

    public static String getDateString(Date d, String delim, String format, String sep) {
        if (d != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            return getDateString(c, delim, format, sep);
        } else {
            return "";
        }
    }

    public static String getDateInMediumDateFormat(Date date) {
        DateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");

        return formatter.format(date);
    }

    public static String getDateInMediumDateAndTimeFormat(Date date) {
        DateFormat formatter = new SimpleDateFormat("MMM dd, yyyy, h:mm a");

        return formatter.format(date);
    }

    public static String getDateString(Calendar c, String delim, String format, String sep) {
        // date delimiter
        if (delim == null) {
            delim = "'";
        }
        // date format, YMD, MDY etc
        if (format == null) {
            format = "YMD";
        }
        // separator of date fields
        if (sep == null) {
            sep = "-";
        }

        if (c != null) {
            String str;
            String year = getFourDigitString(c.get(Calendar.YEAR));
            int month = c.get(Calendar.MONTH) + 1;
            int day = c.get(Calendar.DAY_OF_MONTH);
            if (format.equals("YMD")) {
                str = delim + year + sep + month + sep + day + delim;
            } else if (format.equals("MDY")) {
                str = delim + month + sep + day + sep + year + delim;
            } else if (format.equals("DMY")) {
                str = delim + day + sep + month + sep + year + delim;
            } else {
                str = delim + year + sep + month + sep + day + delim;
            }
            return str;
        }

        return null;
    }

    public static String getDateStringFromCalendar(
            Calendar c,
            String delim,
            String format,
            String sep) {

        return getDateString(c, delim, format, sep);
    }

    public static String getDateStringFromDate(
            Date d,
            String delim,
            String format,
            String sep) {
        if (d != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            return getDateString(c, delim, format, sep);
        } else {
            return "";
        }
    }

    /**
     * Returns a date given an integer representation of a date in the form
     * YYYYMMDD
     *
     * @param dateInt
     * @return
     */
    public static Date getDateFromInt(int dateInt) {
        Calendar c = Calendar.getInstance();
        String dateString = "" + dateInt;

        int year = Integer.parseInt(dateString.substring(0, 4));
        int month = Integer.parseInt(dateString.substring(4, 6));
        int day = Integer.parseInt(dateString.substring(6, 8));

        c.set(year, month - 1, day);
        return c.getTime();
    }

    /**
     * Returns an integer representation of a date in the form YYYYMMDD given a
     * Date object.
     *
     * @param date
     * @return
     */
    public static int getIntFromDate(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        String yearStr = String.format("%04d", c.get(Calendar.YEAR));
        String monthStr = String.format("%02d", c.get(Calendar.MONTH) + 1);
        String dayStr = String.format("%02d", c.get(Calendar.DAY_OF_MONTH));

        return new Integer(yearStr + monthStr + dayStr);
    }

    public static int getPreviousYear() {
        Calendar c;

        // current time and date
        c = Calendar.getInstance();

        return c.get(Calendar.YEAR) - 1;
    }

    public static int getCurrentYear() {
        Calendar c;

        // current time and date
        c = Calendar.getInstance();

        return c.get(Calendar.YEAR);
    }

    public static Date getStartOfPreviousYear() {
        Calendar c, current;

        // current time and date
        current = Calendar.getInstance();
        c = Calendar.getInstance();
        // set start date
        c.set(current.get(Calendar.YEAR) - 1, Calendar.JANUARY, 1, 0, 0, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date getEndOfPreviousYear() {
        Calendar c, current;

        // current time and date
        current = Calendar.getInstance();
        c = Calendar.getInstance();
        // set end date
        c.set(current.get(Calendar.YEAR) - 1, Calendar.DECEMBER, 31, 23, 59, 59);
        c.set(Calendar.MILLISECOND, 999);

        return c.getTime();
    }

    public static Date getStartOfLastMonth() {

        Date date = getStartOfCurrentMonth();

        return BusinessEntityUtils.adjustDate(date, Calendar.MONTH, -1);

    }

    // del
//    public static Date getEndOfLastMonth() {
//        Date date = getEndOfCurrentMonth();
//
//        return BusinessEntityUtils.adjustDate(date, Calendar.MONTH, -1);
//    }
    public static Date getEndOfLastMonth() {
        // getDaysInMonth(int month)
        Calendar c = Calendar.getInstance();

        Date date = getStartOfLastMonth();
        c.setTime(date);

        int month = c.get(Calendar.MONTH);

        return BusinessEntityUtils.adjustDate(date, Calendar.DAY_OF_MONTH, getDaysInMonth(month) - 1);
    }

    public static EntityManager getEntityManager(String PU) {

        try {
            EMF = Persistence.createEntityManagerFactory(PU);
            if (EMF.isOpen()) {
                EntityManager EM = EMF.createEntityManager();
                if (EM.isOpen()) {
                    return EM;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    public static EntityManagerFactory getEntityManagerFactory(String PU) {

        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
            if (emf.isOpen()) {
                return emf;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }

    }

    public static Date adjustDate(Date date, int datePart, int amount) {
        Date adjustedDate = createDate(date);

        Calendar c = Calendar.getInstance();
        c.setTime(adjustedDate);
        c.add(datePart, amount);

        return c.getTime();
    }

    public static int calculatePeriodInWorkingDays(Date startDate, Date endDate) {
        int workDays = 0;

        Calendar startCal = Calendar.getInstance();
        startCal.setTime(createDate(startDate));

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(createDate(endDate));

        // return 0 if end date is earlier that start date
        // inc days if working days is a weekend
        if (startCal.after(endCal)) {
            return 0;
        } else if (startCal.equals(endCal)) {
            return 1;
        } else if (startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                || startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            workDays++;
        }

        // count days excluding sat. and sun.
        while (startCal.before(endCal)) {
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                    && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                workDays++;
            }
            startCal.add(Calendar.DAY_OF_MONTH, 1);
        }

        return workDays;
    }

    public static DatePeriod[] getMonthlyReportDatePeriods(DatePeriod reportingPeriod) {

        // previous reporting period
        DatePeriod previousReportingPeriod
                = new DatePeriod("Previous reporting period", "month", null, null,
                        BusinessEntityUtils.adjustDate(reportingPeriod.getStartDate(), Calendar.MONTH, -1),
                        BusinessEntityUtils.adjustDate(reportingPeriod.getEndDate(), Calendar.MONTH, -1),
                        false, false, true);

        // reporting period year to date
        Calendar now = Calendar.getInstance();
        Calendar reportingPeriodEndDateCalendar = Calendar.getInstance();
        reportingPeriodEndDateCalendar.setTime(reportingPeriod.getEndDate());

        int year = reportingPeriodEndDateCalendar.get(Calendar.YEAR);
        int monthIndex = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);

        DatePeriod reportingPeriodYTD = new DatePeriod("Financial year to date",
                "month",
                null,
                null,
                null,
                BusinessEntityUtils.createDate(year, monthIndex, day),
                false, false, true);

        // reporting period last year
        DatePeriod reportingPeriodLastYear = new DatePeriod("Reporting period last year", "year", null, null,
                BusinessEntityUtils.adjustDate(reportingPeriod.getStartDate(), Calendar.YEAR, -1),
                BusinessEntityUtils.adjustDate(reportingPeriod.getEndDate(), Calendar.YEAR, -1),
                false, false, true);

        // Last financial year to date
        DatePeriod lastFinancialYTD = new DatePeriod("Financial year to date",
                "year",
                null,
                null,
                null,
                BusinessEntityUtils.createDate(year - 1, monthIndex, day),
                false, false, true);
        lastFinancialYTD.setName("Last financial year to date");

        DatePeriod datePeriods[] = {
            reportingPeriod,
            previousReportingPeriod,
            reportingPeriodYTD,
            reportingPeriodLastYear,
            lastFinancialYTD
        };

        return datePeriods;

    }

    public static List<String> removeDuplicatesFromStringList(List<String> list) {
//        HashSet<String> hashSet = new HashSet<String>(list);

        //Assign the HashSet to a new ArrayList
//        return new ArrayList<String>(hashSet);
        return list;
    }

    public static Date getModifiedDate(Date orgDate, int modPeriod, int modAmount) {
        Calendar calendar;

        calendar = Calendar.getInstance();
        calendar.setTime(orgDate);
        calendar.add(modPeriod, modAmount);

        return calendar.getTime();
    }

    public static Boolean areDatesEqual(Date date1, Date date2) {
        if (removeTimeFromDate(date1).equals(removeTimeFromDate(date2))) {
            return true;
        } else {
            return false;
        }
    }

    public static Date removeTimeFromDate(Date date) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

//    @SuppressWarnings("unchecked")
//    public static <T> T findBean(String beanName) {
//        FacesContext context = FacesContext.getCurrentInstance();
//        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
//    }
    /**
     *
     * @param driverClassName // e.g. com.mysql.jdbc.Driver
     * @param url // jdbc:mysql://boshrmapp:3306/jmtstest
     * @param user // e.g. root
     * @param password
     * @return
     */
    public static Connection establishConnection(
            String driverClassName,
            String url,
            String user,
            String password) {

        try {
            Class.forName(driverClassName);
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;

    }

    public static String getPersonFullName(Person person, Boolean reverse) {

        if (person != null) {
            if (person.getFirstName().equals("") && person.getLastName().equals("")) {
                return "";
            }
            if (person.getFirstName().equals("") && !person.getLastName().equals("")) {
                return person.getLastName();
            }
            if (!person.getFirstName().equals("") && person.getLastName().equals("")) {
                return person.getFirstName();
            }
            if (!person.getFirstName().equals("") && !person.getLastName().equals("")) {
                if (reverse) {
                    return person.getLastName() + ", " + person.getFirstName();
                } else {
                    return person.getFirstName() + " " + person.getLastName();
                }
            }

            return "";
        } else {
            return "";
        }
    }

    public static String getContactFullName(Contact contact) {

        return getPersonFullName(contact, false);

    }

    public static String getMainTelFaxEmail(Contact contact) {
        if (contact != null) {
            if (contact.getMainPhoneNumber().getLocalNumber() == null
                    && contact.getMainFaxNumber().getLocalNumber() == null
                    && contact.getInternet().getEmail1() == null) {
                return "";
            }
            if (contact.getMainPhoneNumber().getLocalNumber() == null
                    && contact.getMainFaxNumber().getLocalNumber() == null
                    && contact.getInternet().getEmail1() != null) {
                return contact.getInternet().getEmail1();
            }
            if (contact.getMainPhoneNumber().getLocalNumber() == null
                    && contact.getMainFaxNumber().getLocalNumber() != null
                    && contact.getInternet().getEmail1() != null) {
                return "Fax: " + contact.getMainFaxNumber().getLocalNumber() + "/"
                        + contact.getInternet().getEmail1();
            }
            if (contact.getMainPhoneNumber().getLocalNumber() != null
                    && contact.getMainFaxNumber().getLocalNumber() != null
                    && contact.getInternet().getEmail1() != null) {
                return contact.getMainPhoneNumber().getLocalNumber() + "/"
                        + "Fax: " + contact.getMainFaxNumber().getLocalNumber() + "/"
                        + contact.getInternet().getEmail1();
            }
            if (contact.getMainPhoneNumber().getLocalNumber() != null
                    && contact.getMainFaxNumber().getLocalNumber() != null
                    && contact.getInternet().getEmail1() == null) {
                return contact.getMainPhoneNumber().getLocalNumber() + "/"
                        + "Fax: " + contact.getMainFaxNumber().getLocalNumber();
            }
            if (contact.getMainPhoneNumber().getLocalNumber() != null
                    && contact.getMainFaxNumber().getLocalNumber() == null
                    && contact.getInternet().getEmail1() != null) {
                return contact.getMainPhoneNumber().getLocalNumber() + "/"
                        + contact.getInternet().getEmail1();
            }

            return "";
        } else {
            return "";
        }
    }

    public static String getContactTelAndFax(Contact contact) {
        return ((!contact.getMainPhoneNumber().getLocalNumber().trim().equals(""))
                ? "Tel: " + contact.getMainPhoneNumber().getLocalNumber()
                : "")
                + ((!contact.getMainFaxNumber().getLocalNumber().trim().equals("")
                && !contact.getMainPhoneNumber().getLocalNumber().trim().equals(""))
                ? ", "
                : "")
                + ((!contact.getMainFaxNumber().getLocalNumber().trim().equals(""))
                ? "Fax: " + contact.getMainFaxNumber().getLocalNumber()
                : "");
    }

    public static boolean setupDatabaseConnection(String PU) {

        try {
            EMF = Persistence.createEntityManagerFactory(PU);
            if (EMF.isOpen()) {
                EntityManager EM = EMF.createEntityManager();
                if (EM.isOpen()) {
                    System.out.println("Connected!");
                }
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Connection failed: " + ex);
            return false;
        }

        return true;
    }

    public static EntityManagerFactory createEntityManagerFactory(String PU) {

        try {
            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PU);
            if (entityManagerFactory.isOpen()) {
                return entityManagerFactory;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Connection failed: " + ex);
            return null;
        }
    }

    public static EntityManagerFactory getEMF() {
        return EMF;
    }

//    private static String getDateString(Calendar c, String delim, String format, String sep) {
//        // date delimiter
//        if (delim == null) {
//            delim = "'";
//        }
//        // date format, YMD, MDY etc
//        if (format == null) {
//            format = "YMD";
//        }
//        // separator of date fields
//        if (sep == null) {
//            sep = "-";
//        }
//
//        if (c != null) {
//            String str;
//            String year = getFourDigitString(c.get(Calendar.YEAR));
//            int month = c.get(Calendar.MONTH) + 1;
//            int day = c.get(Calendar.DAY_OF_MONTH);
//            if (format.equals("YMD")) {
//                str = delim + year + sep + month + sep + day + delim;
//            } else if (format.equals("MDY")) {
//                str = delim + month + sep + day + sep + year + delim;
//            } else if (format.equals("DMY")) {
//                str = delim + day + sep + month + sep + year + delim;
//            } else {
//                str = delim + year + sep + month + sep + day + delim;
//            }
//            return str;
//        }
//
//        return null;
//    }
//    public static String getDateStringFromCalendar(
//            Calendar c,
//            String delim,
//            String format,
//            String sep) {
//
//        return getDateString(c, delim, format, sep);
//    }
//
//    public static String getDateStringFromDate(
//            Date d,
//            String delim,
//            String format,
//            String sep) {
//        if (d != null) {
//            Calendar c = Calendar.getInstance();
//            c.setTime(d);
//            return getDateString(c, delim, format, sep);
//        } else {
//            return "";
//        }
//    }
    public static Boolean validateDate(
            Calendar c,
            int minYear,
            int maxYear) {

        if (c != null) {
            int year = c.get(Calendar.YEAR);
            // just year validation for now
            if ((year < minYear) || (year > maxYear)) {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    /**
     * getAlphaCode Generates code for 0 to 701 (A - ZZ)
     *
     * @param number int
     * @return String
     */
    public static String getAlphaCode(long number) {
        return convertNumberToAlphabet(number);
    }

    /**
     * Convert number to spreadsheet style column names e.g. 1 -> B
     *
     * @param num
     * @return
     */
    public static String convertNumberToAlphabet(long num) {
        long numeric = (num) % 26;
        String letter = "" + (char) (65 + numeric);
        long num2 = (long) ((num) / 26);

        if (num2 > 0) {
            return convertNumberToAlphabet(num2 - 1) + letter;
        } else {
            return letter;
        }
    }

    /**
     * Determines the corresponding index for a maximum sequence of characters.
     * The sequence of characters are those that are obtained from a spreadsheet
     * column heading (eg AR).Currently it works for alphabets up to "ZZ" but
     * falls apart beyond that. Need to fix this!
     *
     * @param alphabet
     * @param len
     * @param num
     * @return
     */
    public static int convertAlphabetToNumber(String alphabet, int len, int num) {
        int letterIndex, previousLetterIndex;

        char chars[] = alphabet.toCharArray();
        letterIndex = getLetterIndex("" + chars[len - 1]);
        if ((len - 1) > 0) {
            previousLetterIndex = getLetterIndex("" + chars[len - 2]);
        } else {
            previousLetterIndex = -1;
        }
        num = num + letterIndex;
        if (previousLetterIndex != -1) {
            num = num + (previousLetterIndex + len - 1) * 26;
        }

        if ((len - 2) > 0) {
            String newAlphabet = alphabet.substring(0, len - 1);
            return convertAlphabetToNumber(newAlphabet, newAlphabet.length(), num);
        } else {
            return num;
        }
    }

    // hard coded values to be put in options
    public static void postMail(
            Session mailSession,
            JobManagerUser user,
            String subject,
            String message) throws Exception {

        boolean debug = false;
        Message msg;

        // use default session if none was provided
        if (mailSession == null) {
            //Set the host smtp address
            Properties props = new Properties();
            props.put("mail.smtp.host", "BOSMAIL2.BOS.local"); // tk options // "BOSMAIL2.BOS.local", 172.16.0.19
            //props.put("mail.smtp.auth", "false");
            //props.put("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.ssl.trust", "smtpserver");

            // create some properties and get the default Session
            Session session = Session.getDefaultInstance(props, null);
            session.setDebug(debug);
            msg = new MimeMessage(session);
        } else {
            msg = new MimeMessage(mailSession);
        }

        // set the from and to address
        InternetAddress addressFrom = new InternetAddress("jobmanager@bsj.org.jm", "Job Manager"); // tk for options
        msg.setFrom(addressFrom);

        InternetAddress[] addressTo = new InternetAddress[1];
        if (user != null) {
            addressTo[0] = new InternetAddress(user.getUsername() + "@bsj.org.jm", user.getEmployee().getFirstName() + " " + user.getEmployee().getLastName());
        } else {
            // tk send message to developer. username and full name to be obtained from database in future.
            addressTo[0] = new InternetAddress("dbennett@bsj.org.jm", "Desmond Bennett");
        }

        msg.setRecipients(Message.RecipientType.TO, addressTo);

        // Setting the Subject and Content Type
        msg.setSubject(subject);
        msg.setContent(message, "text/plain");
        Transport.send(msg);
    }

    public static String getMonthShortFormat(Date date) {
        String month = "";
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        switch (c.get(Calendar.MONTH)) {
            case 0:
                month = "Jan";
                break;
            case 1:
                month = "Feb";
                break;
            case 2:
                month = "Mar";
                break;
            case 3:
                month = "Apr";
                break;
            case 4:
                month = "May";
                break;
            case 5:
                month = "Jun";
                break;
            case 6:
                month = "Jul";
                break;
            case 7:
                month = "Aug";
                break;
            case 8:
                month = "Sep";
                break;
            case 9:
                month = "Oct";
                break;
            case 10:
                month = "Nov";
                break;
            case 11:
                month = "Dec";
                break;
            default:
                month = "";
                break;
        }
        return month;
    }

    public static String getYearShortFormat(Date date, int digits) {
        String yearString = "";
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int year = c.get(Calendar.YEAR);
        yearString = yearString + year;

        // get last x digits of year
        yearString = yearString.substring(yearString.length() - digits, yearString.length());

        return yearString;
    }

    public static String getShortenedString(String string, int maxLength) {
        String shortenedString = "";

        if (string.length() > maxLength) {
            shortenedString = string.substring(0, maxLength);
        } else {
            shortenedString = string;
        }

        return shortenedString;
    }

    public static String getColumnPartOfCellReference(String cellReference) {
        String cellCol = "";

        try {
            char chars[] = cellReference.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if (Character.isLetter(c)) {
                    cellCol = cellCol + c;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return cellCol;
        }

    }

    public static String getRowPartOfCellReference(String cellReference) {
        String cellRow = "";

        try {
            char chars[] = cellReference.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if (Character.isDigit(c)) {
                    cellRow = cellRow + c;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return cellRow;
        }

    }

    public static void setExcelCellValue(HSSFWorkbook wb, HSSFSheet sheet, String cellReference,
            Object value, String dataType, HSSFCellStyle style) {

        int rowIndex = Integer.parseInt(getRowPartOfCellReference(cellReference)) - 1;
        int colIndex = convertAlphabetToNumber(
                getColumnPartOfCellReference(cellReference),
                getColumnPartOfCellReference(cellReference).length(),
                0);

        HSSFRow row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        HSSFCell cell = row.getCell(colIndex);
        if (cell == null) {
            cell = row.createCell(colIndex);
        }
        HSSFCellStyle newStyle = setExcelCellDataFormat(wb, dataType, style);

        try {
            if (value != null) {
                if (dataType.equals("java.lang.Long")) {
                    cell.setCellValue((Long) value);
                } else if (dataType.equals("java.lang.Integer")) {
                    cell.setCellValue((Integer) value);
                } else if (dataType.equals("java.lang.Double")) {
                    cell.setCellValue((Double) value);
                } else if (dataType.equals("Currency")) {
                    cell.setCellValue((Double) value);
                } else if (dataType.equals("java.lang.Boolean")) {
                    cell.setCellValue((Boolean) value);
                } else if (dataType.equals("java.lang.String")) {
                    cell.setCellValue(new HSSFRichTextString(value.toString()));
                } else if (dataType.equals("java.util.Date")) {
                    cell.setCellValue((Date) value);
                }

                cell.setCellStyle(newStyle);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Double roundTo2DecimalPlaces(Double value) {

        DecimalFormat df = new DecimalFormat("###.##");

        String strValue = df.format(value);

        return Double.parseDouble(strValue);
    }

    public static long getMediumDateStringAsLong(String dateStr) {
        DateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");

        try {
            Date date = formatter.parse(dateStr);

            return date.getTime();
        } catch (ParseException ex) {
            System.out.println(ex);
            return 0L;
        }

    }

    /**
     * Delete a generic entity
     *
     * @param entity
     * @return
     */
    public static Boolean deleteEntity(EntityManager em, Object entity) {

        try {
            if (entity != null) {
                em.remove(entity);
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

        return true;
    }

}
