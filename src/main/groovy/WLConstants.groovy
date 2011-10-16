/**
 * @author Chris Pearson (chris@twock.com)
 */
public interface WLConstants {

  static final String[] HEADINGS = ["Full Name", null, "Data ID", "Browser Line 1", "Browser Line 2",
          "Browser Width", "Browser Pad.", "Short Name", "Screen Color", "Color Printer", "B&W Printer", "Web Color",
          "Line Style", null]
  static final String[][] DATATYPES = [
          [
                  "DailySummary1 2",
                  "BYTE reserved",    // this will cause the rest of the fields to start on an even address
                  "short dataSpan",   // total # of minutes accounted for by physical records for this day
                  "short hiOutTemp", "short lowOutTemp", // tenths of a degree F
                  "short hiInTemp", "short lowInTemp",   // tenths of a degree F
                  "short avgOutTemp", "short avgInTemp", // tenths of a degree F (integrated over the day)
                  "short hiChill", "short lowChill",     // tenths of a degree F
                  "short hiDew", "short lowDew",         // tenths of a degree F
                  "short avgChill", "short avgDew",      // tenths of a degree F
                  "short hiOutHum", "short lowOutHum",   // tenths of a percent
                  "short hiInHum", "short lowInHum",     // tenths of a percent
                  "short avgOutHum",             // tenths of a percent
                  "short hiBar", "short lowBar",         // thousandths of an inch Hg
                  "short avgBar",                // thousandths of an inch Hg
                  "short hiSpeed", "short avgSpeed",     // tenths of an MPH
                  "short dailyWindRunTotal",     // 1/10'th of an mile
                  "short hi10MinSpeed",          // the highest average wind speed record
                  "BYTE  dirHiSpeed", "BYTE hi10MinDir", // direction code (0-15, 255)
                  "short dailyRainTotal",        // 1/1000'th of an inch
                  "short hiRainRate",            // 1/100'th inch/hr ???
                  "short dailyUVDose",           // 1/10'th of a standard MED
                  "BYTE hiUV",                  // tenth of a UV Index
                  "BYTE timeValues[27]",         // space for 18 time values (see below)
          ],
          [
                  "DailySummary2 3",
                  "BYTE  reserved",   // this will cause the rest of the fields to start on an even address
                  // this field is not used now.
                  "unsigned_short todaysWeather", // bitmapped weather conditions (Fog, T-Storm, hurricane, etc)
                  "short numWindPackets",      // # of valid packets containing wind data,
                  // this is used to indicate reception quality
                  "short hiSolar",             // Watts per meter squared
                  "short dailySolarEnergy",    // 1/10'th Ly
                  "short minSunlight",         // number of accumulated minutes where the avg solar rad > 150
                  "short dailyETTotal",        // 1/1000'th of an inch
                  "short hiHeat", "short lowHeat",     // tenths of a degree F
                  "short avgHeat",             // tenths of a degree F
                  "short hiTHSW", "short lowTHSW",     // tenths of a degree F
                  "short hiTHW", "short lowTHW",       // tenths of a degree F
                  "short integratedHeatDD65",  // integrated Heating Degree Days (65F threshold)
                  // tenths of a degree F - Day
                  // Wet bulb values are not calculated
                  "short hiWetBulb", "short lowWetBulb", // tenths of a degree F
                  "short avgWetBulb",          // tenths of a degree F
                  "BYTE dirBins[24]",          // space for 16 direction bins
                  // (Used to calculate monthly dominant Dir)
                  "BYTE timeValues[15]",       // space for 10 time values (see below)
                  "short integratedCoolDD65",  // integrated Cooling Degree Days (65F threshold)
                  // tenths of a degree F - Day
                  "BYTE  reserved2[11]"
          ],
          [
                  "WeatherDataRecord 1",
                  "BYTE archiveInterval",      // number of minutes in the archive
                  // see below for more details about these next two fields)
                  "BYTE iconFlags",            // Icon associated with this record, plus Edit flags
                  "BYTE moreFlags",            // Tx Id, etc.

                  "short packedTime",          // minutes past midnight of the end of the archive period
                  "short outsideTemp",         // tenths of a degree F
                  "short hiOutsideTemp",       // tenths of a degree F
                  "short lowOutsideTemp",      // tenths of a degree F
                  "short insideTemp",          // tenths of a degree F
                  "short barometer",           // thousandths of an inch Hg
                  "short outsideHum",          // tenths of a percent
                  "short insideHum",           // tenths of a percent
                  "unsigned_short rain",       // number of clicks + rain collector type code
                  "short hiRainRate",          // clicks per hour
                  "short windSpeed",           // tenths of an MPH
                  "short hiWindSpeed",         // tenths of an MPH
                  "BYTE windDirection",        // direction code (0-15, 255)
                  "BYTE hiWindDirection",      // direction code (0-15, 255)
                  "short numWindSamples",      // number of valid ISS packets containing wind data
                  // this is a good indication of reception
                  "short solarRad", "short hisolarRad",// Watts per meter squared
                  "BYTE  UV", "BYTE hiUV",            // tenth of a UV Index
                  "BYTE leafTemp[4]",          // (whole degrees F) + 90
                  "short extraRad",            // used to calculate extra heating effects of the sun in THSW index
                  "short newSensors[6]",       // reserved for future use
                  "BYTE  forecast",            // forecast code during the archive interval
                  "BYTE  ET",                  // in thousandths of an inch
                  "BYTE soilTemp[6]",          // (whole degrees F) + 90
                  "BYTE soilMoisture[6]",      // centibars of dryness
                  "BYTE leafWetness[4]",       // Leaf Wetness code (0-15, 255)
                  "BYTE extraTemp[7]",         // (whole degrees F) + 90
                  "BYTE extraHum[7]"           // whole percent
          ]
  ]
}