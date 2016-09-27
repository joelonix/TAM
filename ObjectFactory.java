package com.ingenico.tam.objects;

import com.ingenico.at.objects.AssetTracking;


/*
	$HeadURL: https://svn.ingenico.com/SPO/Dev/TAM/trunk/src/com/ingenico/tam/objects/ObjectFactory.java $
	$Id: ObjectFactory.java 14236 2015-07-20 11:59:16Z rjadhav $
*/

/**
 * ObjectFactory Class - Methods related to ObjectFactory module
 */
public class ObjectFactory {

	private static Estates estate;
	private static Jobs jobs;
	private static Terminals terminals;
	private static Key key;
	private static Packages packages;
	private static Parameter parameter;
	private static CallScheduling callScheduling;
	private static Scenarios scenarios;
	private static Merchants merchants;
	private static SWConfigurations swConfigurations;
	private static AssetTracking assetTracking;

	/**
	 * Object Creation for Estates. 
	 * @return
	 */
	public static Estates getEstateInstance(){
		if(estate == null){
			estate = new Estates();
		}
		return estate;
	}

	/**
	 * Object Creation for Jobs. 
	 * @return
	 */
	public static Jobs getJobsInstance(){
		if(jobs == null){
			jobs = new Jobs();
		}
		return jobs;
	}

	/**
	 * Object Creation for Terminals. 
	 * @return
	 */
	public static Terminals getTerminalsInstance(){
		if(terminals == null){
			terminals = new Terminals();
		}
		return terminals;
	}

	/**
	 * Object Creation for Key. 
	 * @return
	 */
	public static Key getKeyInstance(){
		if(key == null){
			key = new Key();
		}
		return key;
	}	

	/**
	 * Object Creation for Packages. 
	 * @return
	 */
	public static Packages getPackagesInstance(){
		if(packages == null){
			packages = new Packages();
		}
		return packages;
	}	

	/**
	 * Object Creation for Parameter. 
	 * @return
	 */
	public static Parameter getParameterInstance(){
		if(parameter == null){
			parameter = new Parameter();
		}
		return parameter;
	}	

	/**
	 * Object Creation for CallScheduling. 
	 * @return
	 */
	public static CallScheduling getCallSchedulingInstance(){
		if(callScheduling == null){
			callScheduling = new CallScheduling();
		}
		return callScheduling;
	}

	/**
	 * Object Creation for scenarios. 
	 * @return
	 */
	public static Scenarios getScenariosInstance(){
		if(scenarios == null){
			scenarios = new Scenarios();
		}
		return scenarios;
	}

	/**
	 * Object Creation for merchants. 
	 * @return
	 */
	public static Merchants getMerchantsInstance(){
		if(merchants == null){
			merchants = new Merchants();
		}
		return merchants;
	}

	/**
	 * Object Creation for SWConfigurations. 
	 * @return
	 */
	public static SWConfigurations getSWConfigurationsInstance(){
		if(swConfigurations == null){
			swConfigurations = new SWConfigurations();
		}
		return swConfigurations;
	}
	
	/**
	 * Object Creation for SWConfigurations. 
	 * @return
	 */
	public static AssetTracking getAssetTrackingInstance(){
		if(assetTracking == null){
			assetTracking = new AssetTracking();
		}
		return assetTracking;
	}
}
