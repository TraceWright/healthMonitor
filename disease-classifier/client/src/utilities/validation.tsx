export const VALIDATION_MSG = "Please enter a valid value";
export const isNotEmpty: Function = (value: string): boolean => {
    if (value !== '') { return true; } else { return false; }
}

export const isNumeric: Function =(value: string): boolean => {
    if (typeof parseInt(value) === 'number') { return true; } else { return false; }
}

export const isNumericInRange: Function = (value: string, min: number, max: number): boolean => {
    const v = parseInt(value);
    if (v >= min && v <= max) { return true; } else { return false; }
}

export const inputValidations: Function = (value: string): boolean => {
    return isNotEmpty(value) && isNumeric(value) && isNumericInRange(value, 1, 500);
}