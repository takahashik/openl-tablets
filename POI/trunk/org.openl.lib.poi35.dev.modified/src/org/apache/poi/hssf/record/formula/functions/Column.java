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

package org.apache.poi.hssf.record.formula.functions;

import org.apache.poi.hssf.record.formula.eval.AreaEval;
import org.apache.poi.hssf.record.formula.eval.ErrorEval;
import org.apache.poi.hssf.record.formula.eval.NumberEval;
import org.apache.poi.hssf.record.formula.eval.RefEval;
import org.apache.poi.hssf.record.formula.eval.ValueEval;
//ZS
import org.apache.poi.ss.formula.ArrayEval;

public final class Column implements Function0Arg, Function1Arg,  ArrayMode {
	// end changes ZS
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex) {
        return new NumberEval(srcColumnIndex+1);
    }
    public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0) {
        int rnum;

        if (arg0 instanceof AreaEval) {
            rnum = ((AreaEval) arg0).getFirstColumn();
        } else if (arg0 instanceof RefEval) {
            rnum = ((RefEval) arg0).getColumn();
        } else {
            // anything else is not valid argument
            return ErrorEval.VALUE_INVALID;
        }

        return new NumberEval(rnum + 1);
    }
    public ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
        switch (args.length) {
            case 1:
                return evaluate(srcRowIndex, srcColumnIndex, args[0]);
            case 0:
                return new NumberEval(srcColumnIndex+1);
        }
        return ErrorEval.VALUE_INVALID;
    }
    
//  ZS
    /* (non-Javadoc)
     * @see org.apache.poi.hssf.record.formula.functions.ArrayMode#evaluateInArrayFormula(org.apache.poi.hssf.record.formula.eval.ValueEval[], int, short)
     */
    public ValueEval evaluateInArrayFormula(ValueEval[] evals, int srcCellRow, int srcCellCol) {
    	if ( (evals.length == 1) && (evals[0] instanceof AreaEval) ){
            AreaEval ae = (AreaEval) evals[0];
            ValueEval[][] result= new ValueEval[1][ae.getWidth()];
            for (int c=0; c<ae.getWidth(); c++){
            	result[0][c] = new NumberEval(ae.getFirstColumn()+c+1);
            }
            return new ArrayEval(result);
    	}
    	return evaluate(evals, srcCellRow, srcCellCol);
    			
    }
//  end changes ZS
    
    
}
