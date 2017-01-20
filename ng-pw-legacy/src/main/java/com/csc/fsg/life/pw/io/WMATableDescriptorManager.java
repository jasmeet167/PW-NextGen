package com.csc.fsg.life.pw.io;

import java.util.Hashtable;

import com.csc.fsg.life.pw.util.*;
import com.csc.fsg.life.pw.config.*;
import com.csc.fsg.life.pw.io.descriptor.wma.*;

/* Modifications: T0091, CCCV-E501 ,CCC-VE769, ENH864, ENH922, ENH942 */
/*                ENH808, ENH795, ENH1052, ENH1130, ENH1167, ENH1046 */

public class WMATableDescriptorManager 
	extends TableDescriptorManager 
{
	public void initialize() {
		super.initialize();
		tableDescriptors = new Hashtable<String, TableDescriptor>();
		initializeTableDescriptors();
	}
	
	public void initializeTableDescriptors() {
		InstallConfigBean bean = ProductManager.getInstance().getProduct(Constants.SYSTEM_WMA).getInstallBean();
		if (bean.getAnnuity()) {
			initializeAnnuityDescriptors();
			if ( !bean.isV1mode() ) {
				initializePayoutAnnuityDescriptors();
				initializeMVAAnnuityDescriptors();
			}
		}

		if (bean.getUl())
			initializeULDescriptors();

		if (bean.getTrad())
			initializeTradDescriptors();

		if (bean.getHcc())
			initializeHCCDescriptors();

		if (bean.getTwentyfourXseven())
			initialize24X7Descriptors();

		if (bean.getNbu())
			initializeNBUDescriptors();

		if (isAnyV1ProdInstalled) {
			initializeCommonDescriptors();
			if ( !bean.isV1mode() ) {
				initializePayoutCommonDescriptors();
				initializeMVACommonDescriptors();
				initializeEIACommonDescriptors();
			}
		}
	}

	private void initializeAnnuityDescriptors() {
		isAnyV1ProdInstalled = true;
		addTableDescriptor(new TAB1FDescriptor());
		addTableDescriptor(new TAB2FDescriptor());
		addTableDescriptor(new TAB3FDescriptor());
		addTableDescriptor(new TAB6FDescriptor());
		addTableDescriptor(new TAB7FDescriptor());
		addTableDescriptor(new TAB8FDescriptor());
		addTableDescriptor(new TAC1FDescriptor());
		addTableDescriptor(new TAC2FDescriptor());
		addTableDescriptor(new TAD1FDescriptor());
		addTableDescriptor(new TAD2FDescriptor());
		addTableDescriptor(new TAD3FDescriptor());
		addTableDescriptor(new TAE1FDescriptor());
		addTableDescriptor(new TAE2FDescriptor());
		addTableDescriptor(new TAE3FDescriptor());
		addTableDescriptor(new TAE4FDescriptor());
		addTableDescriptor(new TA04FDescriptor());
		addTableDescriptor(new TA19FDescriptor());
		addTableDescriptor(new TA22FDescriptor());
		addTableDescriptor(new TA33FDescriptor());
		addTableDescriptor(new TA59FDescriptor());
		addTableDescriptor(new TA61FDescriptor());
		addTableDescriptor(new TA62FDescriptor());
		addTableDescriptor(new TW65XDescriptor());
	}

	private void initializeULDescriptors() {
		isAnyV1ProdInstalled = true;
		addTableDescriptor(new T004EDescriptor());
		addTableDescriptor(new T005EDescriptor());
		addTableDescriptor(new T006EDescriptor());
		addTableDescriptor(new T013EDescriptor());
		addTableDescriptor(new T019EDescriptor());
		addTableDescriptor(new T020EDescriptor());
		addTableDescriptor(new T027EDescriptor());
		addTableDescriptor(new T028EDescriptor());
		addTableDescriptor(new T029EDescriptor());
		addTableDescriptor(new T030EDescriptor());
		addTableDescriptor(new T031EDescriptor());
		addTableDescriptor(new T032EDescriptor());
		addTableDescriptor(new T033EDescriptor());
		addTableDescriptor(new T034EDescriptor());
		addTableDescriptor(new T035EDescriptor());
		addTableDescriptor(new T037EDescriptor());
		addTableDescriptor(new T041EDescriptor());
		addTableDescriptor(new T042EDescriptor());
		addTableDescriptor(new T043EDescriptor());
		addTableDescriptor(new T046EDescriptor());
		addTableDescriptor(new T048EDescriptor());
		addTableDescriptor(new T052EDescriptor());
		addTableDescriptor(new T053EDescriptor());
		addTableDescriptor(new T054EDescriptor());
		addTableDescriptor(new T056EDescriptor());
		addTableDescriptor(new T061EDescriptor());
		addTableDescriptor(new T061E1Descriptor());
		addTableDescriptor(new T062EDescriptor());
		addTableDescriptor(new T063EDescriptor());
		addTableDescriptor(new T064EDescriptor());
		addTableDescriptor(new T065EDescriptor());
		//addTableDescriptor(new T066XDescriptor()); /** CCC-21848 Moved to combined tables section*/
		addTableDescriptor(new T083EDescriptor());
		addTableDescriptor(new T086EDescriptor());
		addTableDescriptor(new T087EDescriptor());
		addTableDescriptor(new T089EDescriptor());
		addTableDescriptor(new T097EDescriptor());
	}

	private void initializeTradDescriptors() {
		isAnyV1ProdInstalled = true;
		addTableDescriptor(new TTB1TDescriptor());
		addTableDescriptor(new TTB3TDescriptor());
		addTableDescriptor(new TTB4TDescriptor());
		addTableDescriptor(new TTB5TDescriptor());
		addTableDescriptor(new TTB6TDescriptor());
		addTableDescriptor(new TTB7TDescriptor());
		addTableDescriptor(new TTBATDescriptor());
		addTableDescriptor(new TTBBTDescriptor());
		addTableDescriptor(new TTBCTDescriptor());
		addTableDescriptor(new TTBDTDescriptor());
		addTableDescriptor(new TTC1TDescriptor());
		addTableDescriptor(new TTC7TDescriptor());
		addTableDescriptor(new TTC8TDescriptor());
		addTableDescriptor(new TTD1TDescriptor());
		addTableDescriptor(new TT04TDescriptor());
		addTableDescriptor(new TT06TDescriptor());
		addTableDescriptor(new TT15TDescriptor());
		addTableDescriptor(new TT19TDescriptor());
		addTableDescriptor(new TT24TDescriptor());
		addTableDescriptor(new TT33TDescriptor());
		addTableDescriptor(new TT67TDescriptor());
		addTableDescriptor(new TT72TDescriptor());
		addTableDescriptor(new TT73TDescriptor());
		addTableDescriptor(new TT74TDescriptor());
		addTableDescriptor(new TT76TDescriptor());
		addTableDescriptor(new TT78TDescriptor());
		addTableDescriptor(new TT79TDescriptor());
		addTableDescriptor(new TT81TDescriptor());
		addTableDescriptor(new TT82TDescriptor());
		addTableDescriptor(new TT84TDescriptor());
	}

	private void initializeHCCDescriptors() {
		isAnyV1ProdInstalled = true;
		addTableDescriptor(new TH01HDescriptor());
		addTableDescriptor(new TH11HDescriptor());
		addTableDescriptor(new TH12HDescriptor());
		addTableDescriptor(new TH13HDescriptor());
		addTableDescriptor(new TH21HDescriptor());
		addTableDescriptor(new TH22HDescriptor());
		addTableDescriptor(new TH23HDescriptor());
	}

	private void initializeFAVDescriptors() {
		isAnyV1ProdInstalled = true;
		addTableDescriptor(new T113XDescriptor());
		addTableDescriptor(new T114XDescriptor());
		addTableDescriptor(new T115XDescriptor());

	}

	private void initialize24X7Descriptors() {
		isAnyV1ProdInstalled = true;
		addTableDescriptor(new T116XDescriptor());
	}

	private void initializeNBUDescriptors() {
		isAnyV1ProdInstalled = true;
		addTableDescriptor(new TEC4ZDescriptor());
		addTableDescriptor(new TEC5ZDescriptor());
		addTableDescriptor(new TED7ZDescriptor());
		addTableDescriptor(new TE04ZDescriptor());
	}

	private void initializeCommonDescriptors() {
		initializeFAVDescriptors();
		addTableDescriptor(new T000XDescriptor());
		addTableDescriptor(new T000XADescriptor());
		addTableDescriptor(new T001XDescriptor());
		addTableDescriptor(new T001X1Descriptor());
		addTableDescriptor(new T002XDescriptor());
		addTableDescriptor(new T007XDescriptor());
		addTableDescriptor(new T008XDescriptor());
		addTableDescriptor(new T009XDescriptor());
		addTableDescriptor(new T010XDescriptor());
		addTableDescriptor(new T011XDescriptor());
		addTableDescriptor(new T012XDescriptor());
		addTableDescriptor(new T014XDescriptor());
		addTableDescriptor(new T015XDescriptor());
		addTableDescriptor(new T016XDescriptor());
		addTableDescriptor(new T018XDescriptor());
		addTableDescriptor(new T021XDescriptor());
		addTableDescriptor(new T022XDescriptor());
		addTableDescriptor(new T023XDescriptor());
		addTableDescriptor(new T024XDescriptor());
		addTableDescriptor(new T025XDescriptor());
		addTableDescriptor(new T026XDescriptor());
		addTableDescriptor(new T036XDescriptor());
		addTableDescriptor(new T038XDescriptor());
		addTableDescriptor(new T039XDescriptor());
		addTableDescriptor(new T040XDescriptor());
		addTableDescriptor(new T045XDescriptor());
		addTableDescriptor(new T047XDescriptor());
		addTableDescriptor(new T049XDescriptor());
		addTableDescriptor(new T049X1Descriptor());
		addTableDescriptor(new T051XDescriptor());
		addTableDescriptor(new T051X1Descriptor());
		addTableDescriptor(new T055XDescriptor());
		addTableDescriptor(new T055X1Descriptor());
		addTableDescriptor(new T057XDescriptor());
		addTableDescriptor(new T058XDescriptor());
		addTableDescriptor(new T060XDescriptor());
		addTableDescriptor(new T066XDescriptor()); /** CCC-21848 */
		addTableDescriptor(new T071XDescriptor());
		addTableDescriptor(new T080XDescriptor());
		addTableDescriptor(new T085XDescriptor());
		addTableDescriptor(new T088XDescriptor());
		addTableDescriptor(new T090XDescriptor());
		addTableDescriptor(new T091XDescriptor());
		addTableDescriptor(new T105XDescriptor());
		addTableDescriptor(new T107XDescriptor());
		addTableDescriptor(new T108XDescriptor());
		addTableDescriptor(new T109XDescriptor());
		addTableDescriptor(new T112XDescriptor());
		addTableDescriptor(new T119XDescriptor());
		addTableDescriptor(new T120XDescriptor());
		addTableDescriptor(new T124XDescriptor());
		addTableDescriptor(new T125XDescriptor());
		addTableDescriptor(new T126XDescriptor());
		addTableDescriptor(new T127XDescriptor());
		addTableDescriptor(new T128XDescriptor());
		addTableDescriptor(new TXW1XDescriptor());
		addTableDescriptor(new T0C5XDescriptor());
		addTableDescriptor(new T204BDescriptor());
		addTableDescriptor(new T205BDescriptor());
		addTableDescriptor(new T210BDescriptor());
		addTableDescriptor(new T211BDescriptor());
		addTableDescriptor(new T222BDescriptor());
		addTableDescriptor(new T224BDescriptor());
		addTableDescriptor(new T225BDescriptor());
	}

	private void initializePayoutCommonDescriptors(){
		addTableDescriptor(new TW06XDescriptor());
		addTableDescriptor(new TW08XDescriptor());
		addTableDescriptor(new TW09XDescriptor());
		addTableDescriptor(new TW10XDescriptor());
		addTableDescriptor(new TW67XDescriptor());
		addTableDescriptor(new TW68XDescriptor());
		addTableDescriptor(new TW68X1Descriptor());
		addTableDescriptor(new TW69XDescriptor());
		addTableDescriptor(new TW69X1Descriptor());
		addTableDescriptor(new TW70XDescriptor());
		addTableDescriptor(new TW70X1Descriptor());
	}

	private void initializePayoutAnnuityDescriptors() {
		addTableDescriptor(new TW04XDescriptor());
		addTableDescriptor(new TW05XDescriptor());
		addTableDescriptor(new TW15XDescriptor());
		addTableDescriptor(new TW24XDescriptor());
		addTableDescriptor(new TW30XDescriptor());
		addTableDescriptor(new TW54XDescriptor());
		addTableDescriptor(new TW56XDescriptor());
		addTableDescriptor(new TW57XDescriptor());
		addTableDescriptor(new TW58XDescriptor());
		addTableDescriptor(new TW64XDescriptor());
		addTableDescriptor(new TW66XDescriptor());
		addTableDescriptor(new TW71XDescriptor());
	}

	private void initializeMVAAnnuityDescriptors() {
		addTableDescriptor(new T0D4XDescriptor());
		addTableDescriptor(new T0D5XDescriptor());
	}

	private void initializeMVACommonDescriptors() {
		addTableDescriptor(new T110XDescriptor());
	}

	private void initializeEIACommonDescriptors() {
		addTableDescriptor(new T0C3XDescriptor());
		addTableDescriptor(new T0C6XDescriptor());
		addTableDescriptor(new T0C7XDescriptor());
		addTableDescriptor(new T0C8XDescriptor());
	}

}
