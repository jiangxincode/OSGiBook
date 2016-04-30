package org.osgichina.obr;


import java.net.URL;
import java.util.Date;

import org.apache.felix.bundlerepository.RepositoryAdminImpl;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.obr.Repository;
import org.osgi.service.obr.RepositoryAdmin;
import org.osgi.service.obr.Resolver;
import org.osgi.service.obr.Resource;

/**
 * @author chris
 *
 */
public class Activator implements BundleActivator {

    /**
     * 创建RepositoryAdmin
     * @param bundleContext
     * @return RepositoryAdmin
     */
    private RepositoryAdmin createRepositoryAdmin(BundleContext bundleContext){
        //创建RepositoryAdminImpl实例
    	RepositoryAdminImpl repoAdmin = new RepositoryAdminImpl(bundleContext, null);

        //确保当前的RepositoryAdminImpl实例中没有Repository
        Repository[] repos = repoAdmin.listRepositories();
        for (int i = 0; repos != null && i < repos.length; i++) {
            repoAdmin.removeRepository(repos[i].getURL());
        }

        return repoAdmin;
    }

	/* (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
        URL url = new URL("http", "felix.apache.org", "/obr/releases.xml");

        //创建RepositoryAdmin
        RepositoryAdmin repoAdmin = createRepositoryAdmin(context);
        
        //创建Repository
        Repository repo = (Repository) repoAdmin.addRepository(url);

        //输出Repository的基本信息
        System.out.println("Repo name is " + repo.getName());
        System.out.println("Repo last modified " + new Date(repo.getLastModified()));
        System.out.println("Repo URL is " + repo.getURL());

        //获取Repository中的Resource
        Resource[] res = repo.getResources();
        //生成Resovler
    	Resolver resolver = repoAdmin.resolver();
        if(null != res){
        	//遍历Repository中的Resource，输出Resource的基本信息
	        for(Resource resource : res){
	        	System.out.println(resource.getPresentationName() + "---" + resource.getVersion());
	        	System.out.println("\tId:" + resource.getId());
	        	System.out.println("\tSymbolicName:" + resource.getSymbolicName());
	        	System.out.println("\tURL:" + resource.getURL());
	        	System.out.println();
	        	if(resource.getId().equals("org.osgi.service.obr/1.0.0")){
	        		//将obr这个Resource加入Resolver，在后面会部署
	        		resolver.add(resource);
	        	}
	        }
        }
        
        if(resolver.resolve()){
        	//解析成功，我们进行部署
        	System.out.println("Deploying ...");
        	resolver.deploy(true);
        	System.out.println("Deploy successful");
        }
	}

	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}



}
