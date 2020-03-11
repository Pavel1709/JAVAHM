/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework2;

import java.awt.Dimension;
import java.awt.RenderingHints.Key;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author pmolchanov
 */
public class Import {
 ArrayList nums = new ArrayList();

    public void ImportInfo() throws FileNotFoundException, IOException {
        try {
     Path file_path=FileSystems.getDefault().getPath("HW.xlsx"); //filesystems-работа со всеми файлами компьютера,getDefaul-смотрим папку, где находится наша прога, getpath-получаем путь к ней
     XSSFWorkbook myBook= new XSSFWorkbook(new FileInputStream(file_path.toString()));
     XSSFSheet MySheet= myBook.getSheet("MyVariant");
     int rowCount=MySheet.getPhysicalNumberOfRows();
     HashMap MyExport = new HashMap <String, double[]>();
     XSSFRow headers=MySheet.getRow(0);
     for(int i=0;i<headers.getPhysicalNumberOfCells();i++) {
         XSSFCell header=headers.getCell(i);
         String ColName=header.getStringCellValue();
         double[] value=new double[rowCount];
         int k = 0;
         for (int j=1 ; j< rowCount;j++) {
             value[k]=MySheet.getRow(j).getCell(i).getNumericCellValue();
             k++;
         }
         MyExport.put(ColName, value);
     }
  //  double std = StanrardOtclonenie( MyExport );
     StanrardOtclonenie(MyExport);
        }
      catch (IOException ex) {
         
       //  JFrame j11= new JFrame();
       // JPanel jj1 = new JPanel();
       // j11.add(jj1);
        // JOptionPane dialog = new JOptionPane();
         
        // JLabel L1= new JLabel ();
        // String g ="Ошибка имспорта!";
         //dialog. getOptionPane().add(new JLabel(g));
         JOptionPane.showMessageDialog(null, "Ошибка импорта");
        // L1.setText("Ошибка имспорта!");
        // dialog.add(L1);
        // jj1.add(dialog);
         //j11.setBounds(650 , 400, 400, 300);
         //j11.setVisible(true);
     }
    }

    private void printValues(Map <String, double[]> map) {
for(Map.Entry<String, double[]> pair:map.entrySet()){
    double[] value1=pair.getValue();
    System.out.println(Arrays.toString(value1));
}
    }
    public double StanrardOtclonenie(Map <String, double[]> map) throws IOException {
    
       for(Map.Entry<String, double[]> pair:map.entrySet()){
          double[] value1=pair.getValue();
            DescriptiveStatistics stats = new DescriptiveStatistics();
            for (double value:value1) {
           stats.addValue(value);
          
          
       }
           System.out.println(stats.getStandardDeviation()); 
           nums.add(stats.getStandardDeviation());
           
       }
          // stats.addValue(value1);

    
        //  stats.getStandardDeviation();
        //}
       return 0;
       
    }
        
    
     public void CreateNewBook() throws IOException {
         try {
            Workbook MyWB = new XSSFWorkbook();
            Sheet MyFirstSheet =  MyWB.createSheet("MySheet");
            Row MyFirstRow = MyFirstSheet.createRow(0);
           // Row MySecondRow = MyFirstSheet.createRow(1);
           // Row MyThirdRow = MyFirstSheet.createRow(2);
            Cell CellHelloWorld = MyFirstRow.createCell(0);
            Cell CellInTheSecRow = MyFirstRow.createCell(1);
            Cell CellThree = MyFirstRow.createCell(2);
   
            
            CellHelloWorld.setCellValue((double) nums.get(0));
            CellInTheSecRow.setCellValue((double) nums.get(1));
            CellThree.setCellValue((double) nums.get(2));
          
            
            
          
          
            Path file_path = FileSystems.getDefault().getPath("Data.xlsx");
            FileOutputStream stream = new FileOutputStream(new File(file_path.toString()));
            MyWB.write(stream);
          
        }
     
      catch (IOException ee) {
          /**
        JFrame j12= new JFrame();
        JPanel jj2 = new JPanel();
        j12.add(jj2);
         JOptionPane dialog = new JOptionPane();
         
         JLabel L1= new JLabel ();
         L1.setText("Ошибка экспорта!");
         dialog.add(L1);
         jj2.add(dialog);
         j12.setBounds(650 , 400, 400, 300);
         j12.setVisible(true);      
*/
           JOptionPane.showMessageDialog(null, "Ошибка экспорта");
     }
 
}
}
