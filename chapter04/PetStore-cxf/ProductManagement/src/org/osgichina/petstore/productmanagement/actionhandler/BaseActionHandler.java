package org.osgichina.petstore.productmanagement.actionhandler;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
import org.osgichina.petstore.bootstrap.actionhandler.ActionHandler;
import org.osgichina.petstore.productdal.dao.CategoryDao;
import org.osgichina.petstore.productdal.dao.ItemDao;
import org.osgichina.petstore.productdal.dao.ProductDao;
import org.osgichina.petstore.util.WebResourceMgr;
import org.springframework.osgi.context.BundleContextAware;

/**
 * ����ActionHandler�ĳ��л���
 * ʵ����getHeadInfo�������ṩ������ͼƬ����URL�ķ����������ṩ�˶�WebResourceMgr����Ĺ���
 * �ṩ�˻�ȡDao����ķ���
 * @author chris
 *
 */
public abstract class BaseActionHandler implements ActionHandler, BundleContextAware {
	private BundleContext bundleContext;
	
	private WebResourceMgr webResourceMgr;
	
	private CategoryDao categoryDao;
	
	private ItemDao itemDao;
	
	private ProductDao productDao;

	protected CategoryDao getCategoryDao(){
		return categoryDao;
	}
	
	protected ProductDao getProductDao(){
		return productDao;
	}
	
	protected ItemDao getItemDao(){
		return itemDao;
	}

	public WebResourceMgr getWebResourceMgr() {
		return webResourceMgr;
	}
	public void setWebResourceMgr(WebResourceMgr webResourceMgr) {
		this.webResourceMgr = webResourceMgr;
	}
	
	public void setBundleContext(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
		
		//����ServiceTracker�����ڻ�ȡ����
        ServiceTracker tracker = new ServiceTracker(bundleContext, ProductDao.class.getName(), null) {
            @Override
            public Object addingService(ServiceReference reference) {
                Object result = super.addingService(reference);
                BaseActionHandler.this.productDao = (ProductDao) BaseActionHandler.this.bundleContext.getService(reference);
                return result;
            }
        };
        tracker.open();

        tracker = new ServiceTracker(bundleContext, CategoryDao.class.getName(), null) {
            @Override
            public Object addingService(ServiceReference reference) {
                Object result = super.addingService(reference);
                BaseActionHandler.this.categoryDao = (CategoryDao) BaseActionHandler.this.bundleContext.getService(reference);
                return result;
            }
        };
        tracker.open();

        tracker = new ServiceTracker(bundleContext, ItemDao.class.getName(), null) {
            @Override
            public Object addingService(ServiceReference reference) {
                Object result = super.addingService(reference);
                BaseActionHandler.this.itemDao = (ItemDao) BaseActionHandler.this.bundleContext.getService(reference);
                return result;
            }
        };
        tracker.open();
	}
	/* (non-Javadoc)
	 * @see org.osgichina.petstore.bootstrap.actionhandler.ActionHandler#getHeadInfo(java.lang.String)
	 */
	
	public String getHeadInfo(String resourcePath) {
		StringBuilder sb = new StringBuilder();
		sb.append("<link href=\"");
		sb.append(resourcePath);
		sb.append("/" + webResourceMgr.getRelativeResourcePath() + "/css/petstore-store.css\" rel=\"stylesheet\" type=\"text/css\">\n");
		return sb.toString();
	}
	
	/**
	 * ������ԴURIǰ׺�Լ�ͼƬ�ļ����ƣ�����ͼƬ��������URL��
	 * @param resourcePath
	 * @param imageFile
	 * @return
	 */
	protected String getImagePath(String resourcePath, String imageFile){
		return resourcePath + "/" + getWebResourceMgr().getRelativeResourcePath() + "/images/" + imageFile;
	}

}
