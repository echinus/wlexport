import au.com.bytecode.opencsv.CSVReader
import groovy.io.FileType
import static DataIO.*
import static WLConstants.DATATYPES
import static WLConstants.HEADINGS

class Export {

  public static void main(String[] args) {
    if(args.length < 1) throw new RuntimeException("Expected parameter")
/*
    def dataSets = loadDataSets(args[0])
    for (d in dataSets) {
      println "Data set: ${d.key}"
      for (v in d.value) {
        println v
      }
    }
*/

//    new File(args[0]).eachFileMatch FileType.FILES, ~/.*\.wlk/, {
    new File(args[0]).eachFileMatch FileType.FILES, ~/.*2011-09\.wlk/, {
      println "Reading ${it.getName()}..."
      it.withDataInputStream { stream ->
        stream.readFully(new byte[16])
        int totalRecords = readLong(stream);
        println "Total records: ${totalRecords}"
        for(i in 0..31) {
          def recordsInDay = readShort(stream)
          def startPos = readLong(stream)
          println "day $i: recordsInDay=${recordsInDay}, startPos=${startPos}"
        }
        for(i in 1..totalRecords) {
          readRecord(stream)
        }
      }
    }
  }

  private static Map readRecord(stream) {
    def type = stream.read()
    def record = [:]
    def recordType = DATATYPES.find { Integer.parseInt(it[0].tokenize()[1]) == type }
    if(recordType == null) throw new RuntimeException("Unfound record type " + type)
    recordType[1..recordType.length - 1].each { fieldString ->
      def field = fieldString.tokenize()
      def matcher = field[1] =~ /(.*)\[(\d+)\]/
      if(matcher.matches()) {
        // multiple entries to read
        record[matcher[0][1]] = (1..Integer.valueOf(matcher[0][2])).collect { read(stream, field[0])}
      } else {
        record[field[1]] = read(stream, field[0])
      }
    }
    println "${recordType[0].tokenize()[0]} record (type ${type}): ${record}"
    return record;
  }

  private static Map loadDataSets(String path) {
    def dataSets = [:]
    List<String[]> dataSetCsv = new CSVReader(new FileReader("$path/DataSets.dat"), (char) '\t').readAll();
    String dataSetName = null
    def dataSetEntries = []
    for(line in dataSetCsv) {
      if(dataSetName == null) {
        // first line in a data set is the data set name
        dataSetName = line[0]
      } else if(line[0] == ".") {
        // last line in a data set is the terminator
        dataSets[dataSetName] = dataSetEntries
        dataSetName = null
        dataSetEntries = []
      } else if(line.length != HEADINGS.size()) {
        // catch invalid line lengths
        println "Ignoring invalid line (" + line.length + " cols, expected " + HEADINGS.size() + "): " + line
      } else {
        // build a row map for the current entry
        def row = [:]
        line.eachWithIndex { item, index ->
          if(HEADINGS[index] != null) row[HEADINGS[index]] = item
        }
        dataSetEntries.add(row)
      }
    }
    return dataSets
  }
}
