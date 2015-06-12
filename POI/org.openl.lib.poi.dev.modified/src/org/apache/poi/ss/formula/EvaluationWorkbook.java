/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package org.apache.poi.ss.formula;

import org.apache.poi.ss.formula.ptg.NamePtg;
import org.apache.poi.ss.formula.ptg.NameXPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Abstracts a workbook for the purpose of formula evaluation.<br/>
 *
 * For POI internal use only
 *
 * @author Josh Micich
 */
public interface EvaluationWorkbook extends FormulaParsingWorkbook{
	String getSheetName(int sheetIndex);
	/**
	 * @return -1 if the specified sheet is from a different book
	 */
	int getSheetIndex(EvaluationSheet sheet);
	/**
	 * Finds a sheet index by case insensitive name.
	 * @return the index of the sheet matching the specified name.  -1 if not found
	 */
	int getSheetIndex(String sheetName);

	EvaluationSheet getSheet(int sheetIndex);

    UpdatableEvaluationCell getOrCreateUpdatableCell(String sheetName, int rowIndex, int columnIndex);
	
	/**
	 * @return <code>null</code> if externSheetIndex refers to a sheet inside the current workbook
	 */
	ExternalSheet getExternalSheet(int externSheetIndex);
	int convertFromExternSheetIndex(int externSheetIndex);
	ExternalName getExternalName(int externSheetIndex, int externNameIndex);
	EvaluationName getName(NamePtg namePtg);
    EvaluationName getName(String name, int sheetIndex);
	String resolveNameXText(NameXPtg ptg);
	Ptg[] getFormulaTokens(EvaluationCell cell);
    UDFFinder getUDFFinder();

    Workbook getWorkbook();
    WorkbookEvaluator createExternalWorkbookEvaluator(String workbookName, IExternalWorkbookResolver resolver);
    Workbook createExternalWorkbook(String workbookName, IExternalWorkbookResolver resolver);

    /**
     * Translate "human" ExternalWorkbook Reference into internal if applicable
     * OOXML use index in it's internal table as reference, XLS - does not use
     * any internal ref.
     * 
     * @param refWorkbookName
     * @return
     */
    String translateExternalWorkbookRef(String refWorkbookName);

    /**
     * Translate "internal" ExternalWorkbook Reference into "human" if
     * applicable OOXML use index in it's internal table as reference, XLS -
     * does not use any internal ref.
     * 
     * @param refWorkbookName
     * @return
     */
    String restoreExternalWorkbookName(String refWorkbookName);
    
	class ExternalSheet {
		private final String _workbookName;
		private final String _sheetName;

		public ExternalSheet(String workbookName, String sheetName) {
			_workbookName = workbookName;
			_sheetName = sheetName;
		}
		public String getWorkbookName() {
			return _workbookName;
		}
		public String getSheetName() {
			return _sheetName;
		}
        public boolean equals(Object obj){
            if(obj instanceof ExternalSheet){
                if (_workbookName.equals(((ExternalSheet)obj).getWorkbookName()) && 
                        _sheetName.equals(((ExternalSheet)obj).getSheetName()))
                     return true;
            }        
            return false;
         }
         public int hashCode(){
             return _workbookName.hashCode()+_sheetName.hashCode();
         }
	}
	class ExternalName {
		private final String _nameName;
		private final int _nameNumber;
		private final int _ix;

		public ExternalName(String nameName, int nameNumber, int ix) {
			_nameName = nameName;
			_nameNumber = nameNumber;
			_ix = ix;
		}
		public String getName() {
			return _nameName;
		}
		public int getNumber() {
			return _nameNumber;
		}
		public int getIx() {
			return _ix;
		}
	}
}