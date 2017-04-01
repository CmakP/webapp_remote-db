package a00977249.assignment;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import a00977249.assignment.data.DataManager;
import a00977249.assignment.data.Database;
import a00977249.assignment.data.product.Product;
import a00977249.assignment.data.product.ProductDao;
import a00977249.assignment.util.Utility;
import a00977249.assignment.view.HTMLManager;

/**
 * Servlet implementation class Controller
 */
public class Assignment01Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String PRICE_ERROR = "Enter product price in C$.  Example: ##.#";
	public static final String WEIGHT_ERROR = "Enter product weight in pounds.  Example: ##.#";
	public static final String CODE_ERROR = "Enter the 5 digit product code.  Example: #####";
	public static final String CODE_PATERN = "\\d{5}";
   
	private ProductDao dao;
	private Properties props;
	   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Assignment01Servlet() {
        super();
    }
    
    /**
     * Establishes connection to the remote db and instantiates dao object
     * 
     * @param config contains deploy time info
     * @throws ServletException
     */
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	try {
    		props = new Properties();
    		props.load(new FileInputStream(config.getServletContext().getRealPath("/") + config.getInitParameter("propPath")));
    	} catch (IOException e) {
			e.getMessage();
		}		
	}

	/**
	 * @throws IOException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		try {
			Database.config(props);		
			dao = new ProductDao(getConnection());    		
			if(request.getParameter("insert") != null) {	
	 	   		dao.insert(executeCRUD(request));
	 		} else if(request.getParameter("update") != null) {
	 			dao.update(executeCRUD(request));
	 	    } else if(request.getParameter("delete") != null) {
	 			dao.delete(Integer.parseInt(request.getParameter("id")));
	 		}
	    	
			request.setAttribute("products", loadAllData().getOutputData());
	    	request.setAttribute("protocol", request.getProtocol());
	    	request.setAttribute("metadata", dao.getMetaData());
	 		
	 		response.setContentType("text/html");  
	 		response.setIntHeader("Refresh", 120);
	 		response.setHeader("Cache-Control","no-cache");
	 		response.setHeader("Pragma","co-cache");
	 		
			 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/JSP/results.jsp");
		     dispatcher.forward(request, response); 	 
			 
			 closeConnection();		    
	 	} catch (java.lang.NumberFormatException e) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
	    } catch (IllegalArgumentException ex) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
	    } catch (SQLException e) {
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * Handles the insert statement
	 * 
	 * @param request
	 * @throws SQLException
	 */
	public Product executeCRUD(HttpServletRequest request) throws SQLException {
		
		Product product = new Product();
	    String paramName;
		String paramValue;
		
		Enumeration<String> paramNames = request.getParameterNames();
	    while(paramNames.hasMoreElements()) {   
		  	paramName = paramNames.nextElement();
		    paramValue = request.getParameter(paramName);
		    switch (paramName) {
		    
		        case "Id" :
		    	    product.setId(Integer.parseInt(paramValue));
		        	break;
		        	
		        case "Name" : 
		        	
		    	    if(!Utility.validateInput(paramValue)) {
		    		    throw new IllegalArgumentException("Name is not a nullable field. Enter product name.");
		            } else {
		                product.setName(paramValue);
		            }
     		    break;
     		    
	     	    case "Price" :
	     	    	
		        	if(!Utility.validateInput(paramValue)) {
	    	            throw new IllegalArgumentException(String.format("Price is not a nullable field. %s", PRICE_ERROR));               
	        	    }
		        	double price;
		        	try {
		    	        price = Double.parseDouble(paramValue);
		        	} catch (java.lang.NumberFormatException e) {
		    	    	throw new java.lang.NumberFormatException(String.format("Invalid Entry: '%s'. %s", paramValue, PRICE_ERROR));
		        	}
		    	    if(!Utility.isPositive(price)) {
		    		    throw new IllegalArgumentException(String.format("Invalid Entry: '%s'. Price must be positive.", paramValue));
		        	} else {
		        		product.setPrice(Utility.twoDecimals(price));
		    	    }
		            break;
		            
	    	    case "Weight" :
	    	    	
		        	if(!Utility.validateInput(paramValue)) {
		        	    throw new IllegalArgumentException(String.format("Weight is not a nullable field. %s", WEIGHT_ERROR));
		    	    } 
	    	    	double weight;
		        	try {
		         	    weight = Double.parseDouble(paramValue);
		    	    }  catch (java.lang.NumberFormatException e) {
		        		throw new java.lang.NumberFormatException(String.format("Invalid Input: '%s'. %s", paramValue, WEIGHT_ERROR));
		        	}
		    	    if(!Utility.isPositive(weight)) {
		    		    throw new IllegalArgumentException(String.format("Invalid input: '%s'. Weight must be positive. %s", paramValue, WEIGHT_ERROR));    
		            } else {
		                product.setWeight(Utility.twoDecimals(weight));
		            }           
		            break;
		            
		        case "Code" : 
		        	
		        	if(!Utility.validateInput(paramValue)) {
		    	    	throw new IllegalArgumentException(String.format("Code is not a nullable field. %s", CODE_ERROR));
		        	} else if (!Utility.checkPattern(paramValue, CODE_PATERN)) {
		        		throw new IllegalArgumentException(String.format("Invalid Entry: '%s'. %s", paramValue, CODE_ERROR));  
		    	    } else if(request.getParameter("update") == null && dao.validateCode(Integer.parseInt(paramValue))) {
		    	        throw new IllegalArgumentException("Duplicate code. A product with code '" + paramValue + "' already exists in the table. "
		    					+ "Check your poduct code and try again with a valid code.");
		            } else {
		        		product.setCode(Integer.parseInt(paramValue));
		            }
		        	break;
		        	
		        case "Manufacturer" : 
		        	
		    	    product.setManufacturer(paramValue);
		        	break;
		        	
		        case "MadeIn" : 
		        	
		    	    product.setMadeIn(paramValue);
		        	break;
		        	
		        case "Description" : 
		        	
		        	product.setDescription(paramValue);
		         	break;
		    }
		}
	    return product;
	}
	
	/**
	 * Loads the data from remote db and sets them with the request scope variables
	 * 
	 * @return HTML manager reference
	 * @throws SQLException
	 */
	public HTMLManager loadAllData() throws SQLException {
		HTMLManager hm = new HTMLManager();
		DataManager dm = new DataManager();
		dao.loadData(dm);
		ArrayList<Product> productsList = dm.getProductList();
		for(Product product : productsList) {
			hm.addRowToOutputData(product);
		}
		return hm;
	}
	
	/**
	 * Connects to the remote db
	 * 
	 * @return connection to the remote db
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		return Database.getConnection();
	}
	
	/**
	 * Closes the connects to the remote db
	 * 
	 * @throws SQLException
	 */
	public void closeConnection() throws SQLException {
		Database.closeConnection();
	}
}
