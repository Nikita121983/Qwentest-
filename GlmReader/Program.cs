using System.Windows.Forms;
using GlmReader.UI;

namespace GlmReader;

static class Program
{
    [STAThread]
    static void Main()
    {
        try
        {
            ApplicationConfiguration.Initialize();
            Application.Run(new MainForm());
        }
        catch (Exception ex)
        {
            File.WriteAllText("error.log", ex.ToString());
            MessageBox.Show(ex.ToString(), "Ошибка GlmReader",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
        }
    }
}
