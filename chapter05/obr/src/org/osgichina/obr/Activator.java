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
     * ����RepositoryAdmin
     * @param bundleContext
     * @return RepositoryAdmin
     */
    private RepositoryAdmin createRepositoryAdmin(BundleContext bundleContext){
        //����RepositoryAdminImplʵ��
    	RepositoryAdminImpl repoAdmin = new RepositoryAdminImpl(bundleContext, null);

        //ȷ����ǰ��RepositoryAdminImplʵ����û��Repository
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

        //����RepositoryAdmin
        RepositoryAdmin repoAdmin = createRepositoryAdmin(context);
        
        //����Repository
        Repository repo = (Repository) repoAdmin.addRepository(url);

        //���Repository�Ļ�����Ϣ
        System.out.println("Repo name is " + repo.getName());
        System.out.println("Repo last modified " + new Date(repo.getLastModified()));
        System.out.println("Repo URL is " + repo.getURL());

        //��ȡRepository�е�Resource
        Resource[] res = repo.getResources();
        //����Resovler
    	Resolver resolver = repoAdmin.resolver();
        if(null != res){
        	//����Repository�е�Resource�����Resource�Ļ�����Ϣ
	        for(Resource resource : res){
	        	System.out.println(resource.getPresentationName() + "---" + resource.getVersion());
	        	System.out.println("\tId:" + resource.getId());
	        	System.out.println("\tSymbolicName:" + resource.getSymbolicName());
	        	System.out.println("\tURL:" + resource.getURL());
	        	System.out.println();
	        	if(resource.getId().equals("org.osgi.service.obr/1.0.0")){
	        		//��obr���Resource����Resolver���ں���Ჿ��
	        		resolver.add(resource);
	        	}
	        }
        }
        
        if(resolver.resolve()){
        	//�����ɹ������ǽ��в���
        	System.out.println("Deploying ...");
        	resolver.deploy(true);
        	System.out.println("Deploy successful");
        }
	}

	public void stop(BundleContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}



}
