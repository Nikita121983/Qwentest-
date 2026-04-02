using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;

namespace Qwentest.CSharp;

/// <summary>
/// Точка входа приложения.
/// </summary>
internal class Program
{
    private static void Main(string[] args)
    {
        var services = new ServiceCollection();
        ConfigureServices(services);

        var provider = services.BuildServiceProvider();
        var logger = provider.GetRequiredService<ILogger<Program>>();

        logger.LogInformation("Qwentest C# initialized");
        Console.WriteLine("Qwentest C# initialized");
    }

    private static void ConfigureServices(IServiceCollection services)
    {
        services.AddLogging(builder =>
        {
            builder.AddConsole();
            builder.SetMinimumLevel(LogLevel.Information);
        });
    }
}
