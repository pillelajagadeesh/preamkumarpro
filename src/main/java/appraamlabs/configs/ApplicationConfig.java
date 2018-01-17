package appraamlabs.configs;

public class ApplicationConfig /*implements WebApplicationInitializer */{

	//private static ILogger logger = LoggerFactory.getLogger(ApplicationConfig.class);
	   private static final String DISPATCHER_SERVLET_NAME = "dispatcher";
	   private static final String DISPATCHER_SERVLET_MAPPING = "/";

/*	   @Override
	   public void onStartup(ServletContext servletContext) throws ServletException {
	       AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
	       rootContext.register(ApplicationContext.class);

	       //XmlWebApplicationContext rootContext = new XmlWebApplicationContext();
	       //rootContext.setConfigLocation("classpath:exampleApplicationContext.xml");
//	        rootContext.refresh();
	       ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DISPATCHER_SERVLET_NAME, new DispatcherServlet(rootContext));
	       dispatcher.setLoadOnStartup(1);
	       dispatcher.addMapping(DISPATCHER_SERVLET_MAPPING);
//	        dispatcher.setMultipartConfig(rootContext.getBean(MultipartConfigElement.class));
//	        rootContext.refresh();
//	        IUserAuthService userAuthService = rootContext.getBean(IUserAuthService.class);
	       
//	        AuthenticationTokenProcessingFilter filter = new AuthenticationTokenProcessingFilter(userAuthService);
	       servletContext.addListener(new ContextLoaderListener(rootContext));
	       servletContext.setInitParameter("contextConfigLocation",  
	       	     "classpath*:**///aop-context.xml");
	       //registerHiddenHttpMethodFilter(servletContext);
//	   }
	   
/*	   private void registerHiddenHttpMethodFilter(ServletContext servletContext) {
	       FilterRegistration.Dynamic registration = servletContext.addFilter("hiddenHttpMethodFilter", AuthenticationTokenProcessingFilter.class);
	       registration.addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD),
	               false, DISPATCHER_SERVLET_NAME);
	       logger.logInfo("Initilized the "+DISPATCHER_SERVLET_NAME+" in config");
	   }*/

}
