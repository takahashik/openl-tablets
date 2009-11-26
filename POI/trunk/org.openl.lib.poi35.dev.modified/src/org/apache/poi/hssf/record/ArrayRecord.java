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

package org.apache.poi.hssf.record;

import org.apache.poi.hssf.record.formula.AreaPtg;
import org.apache.poi.hssf.record.formula.AreaPtgBase;
import org.apache.poi.hssf.record.formula.Ptg;
import org.apache.poi.hssf.record.formula.RefPtgBase;
import org.apache.poi.hssf.util.CellRangeAddress8Bit;
import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.HexDump;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.LittleEndianByteArrayOutputStream;
import org.apache.poi.util.LittleEndianInput;
import org.apache.poi.util.LittleEndianOutput;

/**
 * ARRAY (0x0221)<p/>
 *
 * Treated in a similar way to SharedFormulaRecord
 *
 * @author Josh Micich
 * @author vabramovs(VIA) - Array Formula support
 */
public final class ArrayRecord extends SharedValueRecordBase {

	public final static short sid = 0x0221;
	private static final int OPT_ALWAYS_RECALCULATE = 0x0001;
	private static final int OPT_CALCULATE_ON_OPEN  = 0x0002;

	private int	_options;
	private int _field3notUsed;
	private Formula _formula;

	public ArrayRecord(RecordInputStream in) {
		super(in);
		_options = in.readUShort();
		_field3notUsed = in.readInt();
		int formulaTokenLen = in.readUShort();
		int totalFormulaLen = in.available();
		_formula = Formula.read(formulaTokenLen, in, totalFormulaLen);
	}

// VIA
	public ArrayRecord(int options, Formula formula, CellRangeAddress8Bit range ) {
		super(range);
		_options = options;
		_field3notUsed = 0;
		_formula = formula;
	}
// end changes VIA
	public boolean isAlwaysRecalculate() {
		return (_options & OPT_ALWAYS_RECALCULATE) != 0;
	}
	public boolean isCalculateOnOpen() {
		return (_options & OPT_CALCULATE_ON_OPEN) != 0;
	}

	protected int getExtraDataSize() {
		return 2 + 4
			+ _formula.getEncodedSize();
	}
	protected void serializeExtraData(LittleEndianOutput out) {
		out.writeShort(_options);
		out.writeInt(_field3notUsed);
		_formula.serialize(out);
	}

	public short getSid() {
		return sid;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(getClass().getName()).append(" [ARRAY]\n");
		sb.append(" range=").append(getRange().toString()).append("\n");
		sb.append(" options=").append(HexDump.shortToHex(_options)).append("\n");
		sb.append(" notUsed=").append(HexDump.intToHex(_field3notUsed)).append("\n");
		sb.append(" formula:").append("\n");
		Ptg[] ptgs = _formula.getTokens();
		for (int i = 0; i < ptgs.length; i++) {
			Ptg ptg = ptgs[i];
			sb.append(ptg.toString()).append(ptg.getRVAType()).append("\n");
		}
		sb.append("]");
		return sb.toString();
	}
//VIA	
	   /**
     * @return the equivalent {@link Ptg} array that the formula would have, were it not shared.
     */
    public Ptg[] getFormulaTokens() {
        int formulaRow = this.getFirstRow();
        int formulaColumn = this.getLastColumn();
        
        // Use SharedFormulaRecord static method to convert formula
        
        Ptg[] ptgs = _formula.getTokens();
        
        // Convert from relative addressing to absolute
        // because all formulas in array need to be referenced to the same ref/range
        for(int i=0;i<ptgs.length;i++)
        {
	        if(ptgs[i] instanceof  AreaPtgBase){
	        	((AreaPtgBase)ptgs[i]).setFirstRowRelative(false);
	        	((AreaPtgBase)ptgs[i]).setLastRowRelative(false);
	        	((AreaPtgBase)ptgs[i]).setFirstColRelative(false);
	        	((AreaPtgBase)ptgs[i]).setLastColRelative(false);
	        	
	        	}	
	        else if (ptgs[i] instanceof  RefPtgBase){
	        	((RefPtgBase)ptgs[i]).setRowRelative(false);
	        	((RefPtgBase)ptgs[i]).setColRelative(false);
	        	}
        }
        return SharedFormulaRecord.convertSharedFormulas(ptgs, formulaRow, formulaColumn);
    }
// end changes VIA   
	
}
