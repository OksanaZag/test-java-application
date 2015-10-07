/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;


import org.junit.runner.JUnitCore;
import org.junit.runner.notification.RunListener;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import ru.yandex.qatools.allure.annotations.Parameter;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;


/**
 *
 * @author Oksana
 */
@Features("Арифметические действия")
@RunWith(Parameterized.class)
public class MathFunctionTest {
    
    public MathFunctionTest( int op1,  int op2,  String op, int exp)
    {
        operand1 = op1;
        operand2 = op2;
        operation = op;
        expResult = exp;
    }
    
    @Parameter("Оператор 1")
    private int operand1;
    @Parameter("Оператор 2")
    private int operand2;
    @Parameter("Операция")
    private String operation;
    @Parameter("Результат")
    private int expResult;
    
    
    @Parameterized.Parameters
    public static Collection<Object[]> data()
    {
        String line;
        String raw[]=new String[4];  
        List<Object[]> ret = new ArrayList<Object[]>();
        File file = new File("src/test/java/operation.txt");
        try(BufferedReader br=new BufferedReader(new FileReader(file)))
        {
            while((line=br.readLine())!=null)
            {
                    raw=line.split(";");
                    ret.add(new Object[]{ Integer.parseInt(raw[0]), 
                        Integer.parseInt(raw[1]), 
                        raw[2],
                        Integer.parseInt(raw[3]) });
            }
            br.close();
        }
        catch(IOException ex){
            System.out.println("Ощибка: "+ex.getMessage());
        }
        return ret;
        
   }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
        
    /**
     * Test of sum method, of class MathFunction.
     */
    @Title("Тестирование метода: Сложение")
    @Stories("Проверка результата выполнения операции сложение")
    @org.junit.Test
    public void testSum() {
        MathFunction instance = new MathFunction();
        long result = instance.sum(operand1, operand2);
        assertEquals(expResult, result);
    }

    /**
     * Test of division method, of class MathFunction.
     */
    @Title("Тестирование метода: Деление")
    @Stories("Проверка результата выполнения операции деления")
    @org.junit.Test
    public void testDivision() {
        MathFunction instance = new MathFunction();
        long result = instance.division(operand1, operand2);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of multiplication method, of class MathFunction.
     */
    @Title("Тестирование метода: Умножение")
    @Stories("Проверка результата выполнения операции умножения")
    @org.junit.Test
    public void testMultiplication() {
        MathFunction instance = new MathFunction();
        long result = instance.multiplication(operand1, operand2);
        assertEquals(expResult, result);
    }

    /**
     * Test of subtraction method, of class MathFunction.
     */
    @Title("Тестирование метода: Вычитание")
    @Stories("Проверка результата выполнения операции вычитание")
    @org.junit.Test
    public void testSubtraction() {

        MathFunction instance = new MathFunction();
        long result = instance.subtraction(operand1, operand2);
        assertEquals(expResult, result);
    }
    
    public static void main(String[] args) {
        JUnitCore core = new JUnitCore();
        core.addListener(new CalcListener());
        core.run(MathFunctionTest.class);
    }
    
}

class CalcListener extends RunListener {
    @Override
    public void testStarted(Description desc) {
        System.out.println("Started:" + desc.getDisplayName());
    }
 
    @Override
    public void testFinished(Description desc) {
        System.out.println("Finished:" + desc.getDisplayName());
    }
    @Override
    public void testFailure(Failure fail) {
        System.out.println("Failure:" + fail.getDescription().getDisplayName() + " [" + fail.getMessage() + "]");
    }
}

